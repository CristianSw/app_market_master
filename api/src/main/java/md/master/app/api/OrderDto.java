package md.master.app.api;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Order DTO model")
public class OrderDto {
    @Schema(description = "Order id", required = true,example = "1")
    private Long id;
    @Schema(description = "User username mapped to this order id", required = true,example = "bob")
    private String username;
    @Schema(description = "List of order items mapped to this order id ", required = true)
    private List<OrderItemDto> orderItems;
    @Schema(description = "Order delivery address", required = true,example = "Tighina 2/3")
    private String address;
    @Schema(description = "Order phone", required = true,example = "+37379356922")
    private String phone;
    @Schema(description = "Order total price that is get from cart service", required = true,example = "300")
    private BigDecimal totalPrice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
