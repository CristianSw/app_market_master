package md.master.app.core.repositories;

import md.master.app.core.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUsername(String username);
}
