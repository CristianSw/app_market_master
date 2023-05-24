package md.master.app.core.repositories.specifications;

import md.master.app.core.entities.Product;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecifications {

    public static Specification<Product> priceGreaterOrEqualsThan(Integer price){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),price);
    }

    public static Specification<Product> priceLessOrEqualsThan(Integer price){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),price);
    }

    public static Specification<Product> titleLike(String titlePart){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("title"),String.format("%%%s%%",titlePart));
    }


}

