package md.master.app.core.services;

import md.master.app.core.entities.Category;
import md.master.app.core.repositories.CategoryRepository;
import md.master.app.core.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = CategoryService.class)
class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    private final String CATEGORY_TITLE = "TEST";
    private final Long ID = 100L;
    private Category category = new Category();
    @BeforeEach
    void setUp() {
        category = Category.builder().id(ID).title(CATEGORY_TITLE).build();
    }

    @Test
    void findByTitle() {
        Mockito.doReturn(Optional.of(category)).when(categoryRepository).findByTitle(ArgumentMatchers.any());

        Optional<Category> categoryToTest = categoryService.findByTitle(CATEGORY_TITLE);
        Assertions.assertNotNull(categoryToTest);
        Assertions.assertEquals(ID,categoryToTest.get().getId());
        Assertions.assertEquals(CATEGORY_TITLE, categoryToTest.get().getTitle());
    }
}