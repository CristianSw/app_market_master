package md.master.app.core.services;

import lombok.RequiredArgsConstructor;
import md.master.app.core.entities.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;

    public void createOrder(User user){

    }
}
