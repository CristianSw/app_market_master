package md.master.app.core.controllers;

import lombok.RequiredArgsConstructor;
import md.master.app.api.OrderDto;
import md.master.app.core.convertors.OrderConvertor;
import md.master.app.core.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderConvertor orderConvertor;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader(name = "username") String username){
    orderService.createOrder(username);
    }

    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader(name = "username") String username){
    return orderService.findByUsername(username).stream().map(orderConvertor::entityToDto).collect(Collectors.toList());
    }
}
