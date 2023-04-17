package md.master.app.app_market_master.repositories;

import lombok.NoArgsConstructor;
import md.master.app.app_market_master.entities.Cart;
import md.master.app.app_market_master.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NoArgsConstructor

public class CartRepository {
    @Autowired
    private Cart cart;

    public List<Product> findAll(){
        return cart.findAll();
    }

    public void addProduct(Product product){
        cart.add(product);
    }


}
