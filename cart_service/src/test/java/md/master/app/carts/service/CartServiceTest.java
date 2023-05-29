//package md.master.app.carts.service;
//
//import md.master.app.api.CartItemDto;
//import md.master.app.api.ProductDto;
//import md.master.app.carts.convertors.CartItemConvertor;
//import md.master.app.carts.integrations.ProductServiceIntegration;
//import md.master.app.carts.model.Cart;
//import md.master.app.carts.model.CartItem;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//
//@SpringBootTest(classes = {CartService.class})
//class CartServiceTest {
//    @Autowired
//    private CartService cartService;
//    @MockBean
//    private ProductServiceIntegration productServiceIntegration;
//
//    ProductDto productDto = new ProductDto();
//    Cart cart = new Cart();
//    List<CartItem> items = new ArrayList<>();
//    CartItemDto cartItem = new CartItemDto();
//    CartItemConvertor cartItemConvertor = new CartItemConvertor();
//
//
//    @BeforeEach
//    void setUp() {
//        productDto.setId(1L);
//        productDto.setCategoryTitle("Other");
//        productDto.setTitle("TEST");
//        productDto.setPrice(BigDecimal.valueOf(100));
//
//        cartItem.setProductTitle(productDto.getTitle());
//        cartItem.setPrice(productDto.getPrice().intValue());
//        cartItem.setQuantity(2);
//        cartItem.setProductId(productDto.getId());
//        cartItem.setPricePerProduct(productDto.getPrice().intValue());
//
//        items.add(cartItemConvertor.dtoToEntity(cartItem));
//
//        cart.setItems(items);
//        cart.setTotalPrice(BigDecimal.valueOf(200));
//    }
//
//    @Test
//    void getCurrentCart() {
//        Mockito.doReturn(productDto).when(productServiceIntegration).getProductById(any());
//        cartService.add(productServiceIntegration.getProductById(1L).getId());
//
//        Cart cartToTest = cartService.getCurrentCart();
//        Assertions.assertNotNull(cartToTest);
//        Assertions.assertEquals(100,cartToTest.getTotalPrice());
//        Assertions.assertEquals(cart.getItems().size(), cartToTest.getItems().size());
//        Assertions.assertEquals(cart.getItems().get(0).getProductId(), cartToTest.getItems().get(0).getProductId());
//    }
//
//    @Test
//    void add() {
//        Mockito.doReturn(productDto).when(productServiceIntegration).getProductById(any());
//        cartService.add(productDto.getId());
//        Mockito.verify(productServiceIntegration).getProductById(any());
//
//    }
//
//    @Test
//    void clear() {
//        Cart cartToTest = cart;
//
//        cartToTest.clear();
//
//        Assertions.assertNotNull(cartToTest);
//        Assertions.assertEquals(cart.getTotalPrice(),cartToTest.getTotalPrice());
//    }
//
//    @Test
//    void remove() {
//        Cart cartToTest = cart;
//        cartToTest.remove(1L);
//        Assertions.assertNotNull(cartToTest);
//        Assertions.assertEquals(0,cartToTest.getItems().size());
//        Assertions.assertEquals(0,cartToTest.getTotalPrice());
//    }
//
//    @Test
//    void increaseQuantity() {
//        Cart cartToTest = cart;
//
//        cartToTest.increaseQuantity(1L);
//        Assertions.assertNotNull(cartToTest);
//        Assertions.assertEquals(3,cartToTest.getItems().get(0).getQuantity());
//        Assertions.assertEquals(300,cartToTest.getTotalPrice());
//    }
//
//    @Test
//    void decreaseQuantity() {
//        Cart cartToTest = cart;
//
//        cartToTest.decreaseQuantity(1L);
//        Assertions.assertNotNull(cartToTest);
//        Assertions.assertEquals(1,cartToTest.getItems().get(0).getQuantity());
//        Assertions.assertEquals(100,cartToTest.getTotalPrice());
//    }
//}