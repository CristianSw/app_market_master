package md.master.auth.services;

import md.master.app.api.ResourceNotFoundException;
import md.master.auth.entities.Role;
import md.master.auth.repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest(classes = RoleService.class)
class RoleServiceTest {
    @Autowired
    private RoleService roleService;
    @MockBean
    private RoleRepository roleRepository;


    @BeforeEach
    void setUp() {
    }
    @Test
    void testGetUserRole() {
        String roleName = "ROLE_USER";
        Role userRole = Role.builder().id(100L).name(roleName).build();

        when(roleRepository.findByName(roleName)).thenReturn(Optional.of(userRole));

        Role result = roleService.getUserRole();

        assertNotNull(result);
        assertEquals(userRole, result);

        verify(roleRepository, times(1)).findByName(roleName);
        verifyNoMoreInteractions(roleRepository);
    }

    @Test
    void testGetUserRole_RoleNotFound() {
        String roleName = "ROLE_USER";

        when(roleRepository.findByName(roleName)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> roleService.getUserRole());

        verify(roleRepository, times(1)).findByName(roleName);
        verifyNoMoreInteractions(roleRepository);
    }
}