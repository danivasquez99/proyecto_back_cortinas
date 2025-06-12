package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Order;
import cr.ac.ucr.ie.lenguajes_2025.services.OrderServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

     private final OrderServices orderServices;

    @Autowired
    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping
    public ResponseEntity<LinkedList<Order>> getAllOrders() {
        return ResponseEntity.ok(orderServices.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Order order = orderServices.findOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody Order order) {
        orderServices.addOrder(order);
        return ResponseEntity.status(201).build(); // <-- HTTP 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable int id, @RequestBody Order order) {
        order.setIdOrder(id); // Asegura que el ID esté definido correctamente
        orderServices.updateOrder(order);
        return ResponseEntity.noContent().build(); // <-- HTTP 204
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderServices.deleteOrderById(id);
        return ResponseEntity.noContent().build(); // <-- HTTP 204
    }
}