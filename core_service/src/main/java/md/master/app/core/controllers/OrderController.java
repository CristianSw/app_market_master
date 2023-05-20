package md.master.app.core.controllers;

import lombok.RequiredArgsConstructor;
import md.master.app.core.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader(name = "username") String username){
    orderService.createOrder(username);
    }
}
