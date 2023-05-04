package md.master.app.app_market_master.repositories;

import md.master.app.app_market_master.entities.Category;
import md.master.app.app_market_master.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByTitle(String title);
}
