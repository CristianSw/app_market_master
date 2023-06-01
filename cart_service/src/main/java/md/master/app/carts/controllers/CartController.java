package md.master.app.carts.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;


import md.master.app.api.CartDto;
import md.master.app.api.StringResponse;
import md.master.app.carts.convertors.CartConverter;
import md.master.app.carts.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Schema(name = "Cart", description = "Methods to interact with cart")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @Operation(
            summary = "Request to generate uuid for guest user",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/generate_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }



    @Operation(
            summary = "Request to generate uuid for guest user",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) @Parameter(description = "User username get from header", required = false, example = "bob") String username,
                                  @PathVariable @Parameter(description = "uuid generated for guest user", required = true) String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(targetUuid));
    }

    @Operation(
            summary = "Request to generate uuid for guest user",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/{uuid}/add/{id}")
    public void addProductToCart(@RequestHeader(name = "username", required = false) @Parameter(description = "User username get from header", required = false, example = "bob") String username,
                                 @PathVariable  @Parameter(description = "uuid generated for guest user", required = true) String uuid,
                                 @PathVariable @Parameter(description = "product id to be removed", required = true, example = "1") Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.add(targetUuid, id);
    }

    @Operation(
            summary = "Request to generate uuid for guest user",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/{uuid}/inc/{id}")
    public void increaseQuantity(@RequestHeader(name = "username", required = false) @Parameter(description = "User username get from header", required = false, example = "bob") String username,
                                 @PathVariable @Parameter(description = "uuid generated for guest user", required = true) String uuid,
                                 @PathVariable @Parameter(description = "product id to be removed", required = true, example = "1") Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.increaseQuantity(targetUuid, id);
    }


    @Operation(
            summary = "Request to generate uuid for guest user",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/{uuid}/dec/{id}")
    public void decreaseQuantity(@RequestHeader(name = "username", required = false) @Parameter(description = "User username get from header", required = false, example = "bob") String username,
                                 @PathVariable @Parameter(description = "uuid generated for guest user", required = true) String uuid,
                                 @PathVariable @Parameter(description = "product id to be removed", required = true, example = "1") Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.decreaseQuantity(targetUuid, id);
    }

    @Operation(
            summary = "Request to generate uuid for guest user",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/clear")
    public void clear(@RequestHeader(name = "username", required = false) @Parameter(description = "User username get from header", required = false, example = "bob") String username,
                      @PathVariable @Parameter(description = "uuid generated for guest user", required = true) String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.clear(targetUuid);
    }

    @Operation(
            summary = "Request to remove product by id from cart",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{uuid}/remove/{id}")
    public void removeFromCart(@RequestHeader(name = "username", required = false) @Parameter(description = "User username get from header", required = false, example = "bob") String username,
                               @PathVariable @Parameter(description = "uuid generated for guest user", required = true) String uuid,
                               @PathVariable @Parameter(description = "product id to be removed", required = true, example = "1")Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.remove(targetUuid, id);
    }


    private String getCartUuid(String username, String uuid) {
        if (username != null) {
            return username;
        }
        return uuid;
    }
}