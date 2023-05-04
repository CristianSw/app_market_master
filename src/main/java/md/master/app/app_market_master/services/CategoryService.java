package md.master.app.app_market_master.services;

import lombok.RequiredArgsConstructor;
import md.master.app.app_market_master.entities.Category;
import md.master.app.app_market_master.entities.Product;
import md.master.app.app_market_master.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public Optional<Category> findByTitle(String title){
    return categoryRepository.findByTitle(title);
    }
}
