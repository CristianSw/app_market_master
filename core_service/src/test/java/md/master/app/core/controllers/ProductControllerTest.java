package md.master.app.core.controllers;

import md.master.app.core.entities.Category;
import md.master.app.core.entities.Product;
import md.master.app.core.repositories.ProductRepository;
import md.master.app.core.services.CategoryService;
import md.master.app.core.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private CategoryService categoryService;

    Category category = new Category();

    private List<Product> products = new ArrayList<>();
    private Product product = new Product();

    @BeforeEach
    void setUp() {
        Category category = Category.builder().id(11L).title("Other").build();
        products.add(Product.builder().id(1L).title("test1").price(100).category(category).build());
        products.add(Product.builder().id(2L).title("test2").price(200).category(category).build());
        products.add(Product.builder().id(3L).title("test3").price(300).category(category).build());
        products.add(Product.builder().id(4L).title("test4").price(400).category(category).build());
        products.add(Product.builder().id(5L).title("test5").price(500).category(category).build());
        products.add(Product.builder().id(6L).title("test6").price(600).category(category).build());
        products.add(Product.builder().id(7L).title("test7").price(700).category(category).build());
        products.add(Product.builder().id(8L).title("test8").price(800).category(category).build());
        products.add(Product.builder().id(9L).title("test9").price(900).category(category).build());
        products.add(Product.builder().id(10L).title("test10").price(999).category(category).build());


        product = Product.builder().id(5L).title("test5").price(500).category(category).build();
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void findAllProducts() throws Exception {
        given(productRepository.findAll()).willReturn(products);
        mvc.perform(get("/api/v1/products")
                        .header("username", "testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].id", is(products.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].title", is(products.get(0).getTitle())))
                .andExpect(jsonPath("$[0].price", is(products.get(0).getPrice())))
                .andExpect(jsonPath("$[9].id", is(products.get(9).getId().intValue())))
                .andExpect(jsonPath("$[9].title", is(products.get(9).getTitle())))
                .andExpect(jsonPath("$[9].price", is(products.get(9).getPrice())));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void findProductById() throws Exception {
        given(productService.findById(1L)).willReturn(Optional.of(products.get(0)));

        mvc.perform(get("/api/v1/products/1")
                        .header("username", "testuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(products.get(0).getId().intValue()))
                .andExpect(jsonPath("$.title").value(products.get(0).getTitle()))
                .andExpect(jsonPath("$.price").value(products.get(0).getPrice()));
    }
}