package md.master.app.core.services;

import lombok.RequiredArgsConstructor;
import md.master.app.api.CartDto;
import md.master.app.core.entities.Order;
import md.master.app.core.entities.OrderItem;
import md.master.app.core.entities.User;
import md.master.app.core.integrations.CartServiceIntegration;
import md.master.app.core.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(User user) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart().get(); //cartServiceIntegration.getCurrentCart();
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setAddress("test");
        order.setPhone("+37379356922");
        order.setOrderItems(cartDto.getItems().stream().map(cartItem -> new OrderItem(
                        productService.findById(cartItem.getProductId()).get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()))
                .collect(Collectors.toList()));
        orderRepository.save(order);
        cartServiceIntegration.clearCurrentCart();

    }
}
