package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Order;
import cr.ac.ucr.ie.lenguajes_2025.domain.OrderTracking;
import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import cr.ac.ucr.ie.lenguajes_2025.repository.OrderRepository;
import cr.ac.ucr.ie.lenguajes_2025.repository.OrderTrackingRepository;
import cr.ac.ucr.ie.lenguajes_2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderTrackingService {

    @Autowired
    private OrderTrackingRepository trackingRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    // Crear nuevo registro de tracking
    public OrderTracking createTracking(Integer idOrder, Integer idAdmin, Integer idClient) {
        Optional<Order> order = orderRepository.findById(idOrder);
        Optional<User> admin = userRepository.findById(idAdmin);
        Optional<User> client = userRepository.findById(idClient);

        if (order.isEmpty() || admin.isEmpty() || client.isEmpty()) {
            throw new IllegalArgumentException("Orden, admin o cliente no encontrados");
        }

        OrderTracking tracking = new OrderTracking();
        tracking.setOrder(order.get());
        tracking.setAdmin(admin.get());
        tracking.setClient(client.get());

        return trackingRepository.save(tracking);
    }

    // Leer todos
    public List<OrderTracking> getAllTrackings() {
        return trackingRepository.findAll();
    }

    // Leer por ID
    public Optional<OrderTracking> getTrackingById(Integer id) {
        return trackingRepository.findById(id);
    }

    // Actualizar
    public OrderTracking updateTracking(Integer id, OrderTracking newTracking) {
        return trackingRepository.findById(id).map(tracking -> {
            tracking.setOrder(newTracking.getOrder());
            tracking.setAdmin(newTracking.getAdmin());
            tracking.setClient(newTracking.getClient());
            return trackingRepository.save(tracking);
        }).orElseThrow(() -> new IllegalArgumentException("Seguimiento no encontrado con ID: " + id));
    }

    // Eliminar
    public void deleteTracking(Integer id) {
        if (!trackingRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe un seguimiento con ID: " + id);
        }
        trackingRepository.deleteById(id);
    }
}
