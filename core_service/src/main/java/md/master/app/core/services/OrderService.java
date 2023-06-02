package md.master.app.core.services;

import lombok.RequiredArgsConstructor;
import md.master.app.api.CartDto;
import md.master.app.core.entities.Order;
import md.master.app.core.entities.OrderItem;
import md.master.app.core.integrations.CartServiceIntegration;
import md.master.app.core.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public Order createOrder(String username) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setOrderItems(cartDto.getItems().stream().map(cartItem -> new OrderItem(
                        productService.findById(cartItem.getProductId()).get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()))
                .collect(Collectors.toList()));
        orderRepository.save(order);
        cartServiceIntegration.clearCurrentCart(username);
        return order;
    }

    public List<Order> findByUsername(String username){
        return orderRepository.findByUsername(username);
    }
    public Optional<Order> findById(Long orderId){
        return orderRepository.findById(orderId);
    }
}
