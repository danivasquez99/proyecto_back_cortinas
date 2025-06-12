package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Order;
import cr.ac.ucr.ie.lenguajes_2025.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class OrderServices {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServices(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public LinkedList<Order> getAllOrders() {
        return new LinkedList<>(orderRepository.findAll());
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public void updateOrder(Order order) {
        orderRepository.save(order); 
    }

    public void deleteOrderById(int idOrder) {
        orderRepository.deleteById(idOrder);
    }

    public Order findOrderById(int idOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(idOrder);
        return optionalOrder.orElse(null);
    }
}
