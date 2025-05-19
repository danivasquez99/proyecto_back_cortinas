package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.OrderDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.Order;

import java.util.LinkedList;

/**
 * Servicio de órdenes utilizando directamente la implementación DAO:
 * cr.ac.ucr.ie.lenguajes_2025.dao_implement.OrderDAOImplement
 *
 * @author Tony
 */
public class OrderServices {

    private final OrderDAOImplement orderDAO;

    public OrderServices() {
        this.orderDAO = new OrderDAOImplement();
    }

    /**
     * Devuelve todas las órdenes (ya vienen tipadas como Order).
     * @return 
     */
    public LinkedList<Order> getAllOrders() {
        
        return orderDAO.getAll();
        
    }

    /**
     * Inserta una nueva orden.
     */
    public void addOrder(Order order) {
        orderDAO.insert(order);
    }

    /**
     * Actualiza una orden existente.
     */
    public void updateOrder(Order order) {
        orderDAO.update(order);
    }

    /**
     * Elimina una orden por su ID.
     */
    public void deleteOrderById(int idOrder) {
        orderDAO.deleteById(idOrder);
    }

    /**
     * Busca y devuelve una orden por ID, o null si no existe.
     */
    public Order findOrderById(int idOrder) {
        return orderDAO.findById(idOrder);
    }
}