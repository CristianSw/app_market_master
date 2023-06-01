package md.master.app.core.convertors;

import lombok.RequiredArgsConstructor;
import md.master.app.api.OrderDto;
import md.master.app.api.OrderItemDto;
import md.master.app.core.entities.Order;
import md.master.app.core.entities.OrderItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConvertor {

    private final OrderItemConvertor orderItemConvertor;

    public OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setAddress(order.getAddress());
        orderDto.setUsername(order.getUsername());
        orderDto.setPhone(order.getPhone());
        orderDto.setOrderItems(order.getOrderItems().stream().map(orderItemConvertor::entityToDto).collect(Collectors.toList()));
        orderDto.setTotalPrice(order.getTotalPrice());
        return orderDto;
    }

//    public Order dtoToEntity(OrderDto orderDto){
//        OrderItem orderItem = new OrderItem();
//        orderItem.setId(orderItemDto.getId());
//        orderItem.setQuantity(orderItemDto.getQuantity());
//        orderItem.setPrice(orderItemDto.getPrice());
//        orderItem.setPricePerProduct(orderItemDto.getPricePerProduct());
//        return orderItem;
//    }
}
