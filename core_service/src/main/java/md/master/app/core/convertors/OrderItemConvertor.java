package md.master.app.core.convertors;

import md.master.app.api.OrderItemDto;
import md.master.app.core.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConvertor {

    public OrderItemDto entityToDto(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setOrderId(orderItem.getId());

        return orderItemDto;
    }

    public OrderItem dtoToEntity(OrderItemDto orderItemDto){
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setPricePerProduct(orderItemDto.getPricePerProduct());
        return orderItem;
    }
}
