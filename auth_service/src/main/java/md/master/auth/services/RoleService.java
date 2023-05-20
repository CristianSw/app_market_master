package md.master.auth.services;

import lombok.RequiredArgsConstructor;
import md.master.auth.entities.Role;
import md.master.auth.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}