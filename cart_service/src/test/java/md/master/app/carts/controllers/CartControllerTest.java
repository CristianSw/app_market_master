package md.master.app.carts.controllers;


import md.master.app.carts.model.Cart;
import md.master.app.carts.model.CartItem;
import md.master.app.carts.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CartService cartService;

    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = Cart.builder().items(List.of(CartItem.builder()
                        .productId(1L)
                        .productTitle("TItle")
                        .price(100)
                        .pricePerProduct(100)
                        .quantity(1)
                        .build()))
                .totalPrice(100).build();
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void getCurrentCart() throws Exception {
        given(cartService.getCurrentCart()).willReturn(cart);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/cart")
                        .header("username", "testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.totalPrice").value(cart.getTotalPrice()));
    }

    @Test
    void getCurrentCartUnauthorized() throws Exception {
        given(cartService.getCurrentCart()).willReturn(cart);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/cart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void addProductToCart() throws Exception {
        Long productId = 1L;

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/cart/add/{id}", productId)
                        .header("username", "testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cartService, times(1)).add(productId);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void increaseQuantity() throws Exception {
        Long productId = 1L;

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/cart/inc/{id}", productId)
                        .header("username", "testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cartService, times(1)).increaseQuantity(productId);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void decreaseQuantity() throws Exception {
        Long productId = 1L;

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/cart/dec/{id}", productId)
                        .header("username", "testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cartService, times(1)).decreaseQuantity(productId);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void clear() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/cart/clear")
                        .header("username", "testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cartService, times(1)).clear();
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void removeFromCart() throws Exception {
        Long productId = 1L;

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/cart/remove/{id}", productId)
                        .header("username", "testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cartService, times(1)).remove(productId);
    }
}