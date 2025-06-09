package cr.ac.ucr.ie.lenguajes_2025.repository;

import cr.ac.ucr.ie.lenguajes_2025.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByStatus(String status);

    List<Order> findByTotalGreaterThan(Float amount);

    List<Order> findByOrderDateBetween(java.sql.Date start, java.sql.Date end);
}
