package md.master.app.core.controllers;

import md.master.app.core.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private OrderController orderController;
    @MockBean
    private OrderService orderService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateOrder() {
        String username = "john.doe";

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("username", username);
        MockHttpServletResponse response = new MockHttpServletResponse();

        orderController.createOrder(request.getHeader("username"));

        verify(orderService, times(1)).createOrder(username);
        verifyNoMoreInteractions(orderService);

        assertEquals(200, response.getStatus());
    }
}