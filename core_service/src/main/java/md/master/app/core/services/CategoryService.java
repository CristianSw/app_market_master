package md.master.app.core.services;

import lombok.RequiredArgsConstructor;
import md.master.app.core.entities.Category;
import md.master.app.core.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public Optional<Category> findByTitle(String title){
    return categoryRepository.findByTitle(title);
    }
}
