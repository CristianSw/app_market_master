//package md.master.app.core.services;
//
//import md.master.app.api.CartDto;
//import md.master.app.api.CartItemDto;
//import md.master.app.core.entities.Category;
//import md.master.app.core.entities.Order;
//import md.master.app.core.entities.Product;
//import md.master.app.core.integrations.CartServiceIntegration;
//import md.master.app.core.repositories.OrderRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//
//@SpringBootTest(classes = OrderService.class)
//class OrderServiceTest {
//    @Autowired
//    private OrderService orderService;
//
//    @MockBean
//    private ProductService productService;
//
//    @MockBean
//    private CartServiceIntegration cartServiceIntegration;
//    @MockBean
//    private OrderRepository orderRepository;
//
//    Product product = new Product();
//    Category category = new Category();
//    CartItemDto cartItem = new CartItemDto();
//    List<CartItemDto> items = new ArrayList<>();
//    CartDto cart = new CartDto();
//    @BeforeEach
//    void setUp() {
//        category = Category.builder().id(123L).title("Other").build();
//        product = Product.builder().id(112024L).title("Test").price(100).category(category).build();
//
//        cartItem.setProductTitle(product.getTitle());
//        cartItem.setPrice(product.getPrice());
//        cartItem.setQuantity(2);
//        cartItem.setProductId(product.getId());
//        cartItem.setPricePerProduct(product.getPrice());
//
//        items.add(cartItem);
//
//        cart.setItems(items);
//        cart.setTotalPrice(100);
//    }
//
//    @Test
//    void createOrder() {
//        Mockito.doReturn(cart).when(cartServiceIntegration).getCurrentCart();
//        Mockito.doReturn(Optional.of(product)).when(productService).findById(112024L);
//
//        Order order = orderService.createOrder("Bob");
//        Assertions.assertEquals(order.getTotalPrice(),100);
//        Assertions.assertEquals(order.getUsername(), "Bob");
//        Assertions.assertEquals(order.getOrderItems().size(),1);
//        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());
//    }
//}