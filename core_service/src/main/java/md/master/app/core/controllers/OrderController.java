package md.master.app.core.controllers;

import lombok.RequiredArgsConstructor;
import md.master.app.core.entities.User;
import md.master.app.core.services.OrderService;
import md.master.app.core.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
    orderService.createOrder(user);
    }
}
