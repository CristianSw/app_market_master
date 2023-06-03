package md.master.app.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import md.master.app.api.OrderDto;
import md.master.app.api.ResourceNotFoundException;
import md.master.app.core.convertors.OrderConvertor;
import md.master.app.core.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Schema(name = "Order", description = "Methods to work with orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderConvertor orderConvertor;
    @Operation(
            summary = "Request to create order",
            responses = {
                    @ApiResponse(
                            description = "created", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader(name = "username") @Parameter(description = "User username ", required = true, example = "bob") String username){
    orderService.createOrder(username);
    }


    @Operation(
            summary = "Request to get user orders",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader(name = "username")  @Parameter(description = "User username get from header", required = true, example = "bob") String username){
    return orderService.findByUsername(username).stream().map(orderConvertor::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderConvertor.entityToDto(orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("ORDER 404")));
    }
}
