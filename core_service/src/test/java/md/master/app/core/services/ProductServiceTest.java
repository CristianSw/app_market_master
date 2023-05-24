package md.master.app.core.services;

import md.master.app.core.convertors.ProductConverter;
import md.master.app.core.entities.Category;
import md.master.app.core.entities.Product;
import md.master.app.core.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {ProductService.class})
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryService categoryService;

    ProductConverter productConverter = new ProductConverter(categoryService);

    List<Product> products = new ArrayList<>();
    Product returnedProduct = new Product();
    Category category = new Category();


    @BeforeEach
    void setUp() {
        category = Category.builder().id(1L).title("Other").build();
        products.add(Product.builder().id(1L).title("title1").price(100).category(category).build());
        products.add(Product.builder().id(2L).title("title2").price(200).category(category).build());
        products.add(Product.builder().id(3L).title("title3").price(300).category(category).build());
        products.add(Product.builder().id(4L).title("title4").price(400).category(category).build());
        products.add(Product.builder().id(5L).title("title5").price(500).category(category).build());
        returnedProduct = Product.builder().id(3L).title("title3").price(300).category(category).build();
    }

    @Test
    void findAll() {
        Mockito.doReturn(products).when(productRepository).findAll();
        Specification<Product> spec = Specification.where(null);

        List<Product> productsToTest = productService.findAll(spec,1).getContent();
        Assertions.assertEquals(5, productsToTest.size());
        Assertions.assertEquals(1L, productsToTest.get(0).getId());
        Assertions.assertEquals("title1", productsToTest.get(0).getTitle());
        Assertions.assertEquals(100, productsToTest.get(0).getPrice());
        Assertions.assertEquals(1L, productsToTest.get(0).getCategory().getId());
        Assertions.assertEquals("Other", productsToTest.get(0).getCategory().getTitle());
        Assertions.assertEquals(5L, productsToTest.get(4).getId());
        Assertions.assertEquals("title5", productsToTest.get(4).getTitle());
        Assertions.assertEquals(500, productsToTest.get(4).getPrice());
        Assertions.assertEquals(1L, productsToTest.get(4).getCategory().getId());
        Assertions.assertEquals("Other", productsToTest.get(4).getCategory().getTitle());
    }

    @Test
    void findById() {
        Mockito.doReturn(Optional.of(products.get(2))).when(productRepository).findById(3L);

        Optional<Product> productToTest = productService.findById(3L);

        Assertions.assertNotNull(productToTest);
        Assertions.assertEquals(3L, productToTest.get().getId());
        Assertions.assertEquals("title3", productToTest.get().getTitle());
        Assertions.assertEquals(300, productToTest.get().getPrice());
        Assertions.assertEquals(1L, productToTest.get().getCategory().getId());
        Assertions.assertEquals("Other", productToTest.get().getCategory().getTitle());
    }

    @Test
    void deleteById() {
        productService.deleteById(3L);
        verify(productRepository).deleteById(any());
    }

    @Test
    void createNewProduct() {
        Mockito.doReturn(Optional.of(category)).when(categoryService).findByTitle("Other");
        Product productToTest = productService.createNewProduct(productConverter.entityToDto(returnedProduct));
        Assertions.assertNotNull(productToTest);
        Assertions.assertEquals(returnedProduct.getTitle(), productToTest.getTitle());
        Assertions.assertEquals(returnedProduct.getPrice(), productToTest.getPrice());
        Assertions.assertEquals(returnedProduct.getCategory().getId(), productToTest.getCategory().getId());
        Assertions.assertEquals(returnedProduct.getCategory().getTitle(), productToTest.getCategory().getTitle());

    }
}