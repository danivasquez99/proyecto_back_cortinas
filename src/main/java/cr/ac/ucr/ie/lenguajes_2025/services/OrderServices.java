package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao.OrderDAO;
import cr.ac.ucr.ie.lenguajes_2025.dao_implement.OrderDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.Order;

import java.util.LinkedList;

/**
 *
 * @author Tony
 */
public class OrderServices {

    private final OrderDAO orderDAO;

    public OrderServices() {
        this.orderDAO = new OrderDAOImplement();
    }

    public LinkedList<Order> getAllOrders() {
        LinkedList<Order> orders = new LinkedList<>();
        for (Object obj : orderDAO.getAll()) {
            if (obj instanceof Order) {
                orders.add((Order) obj);
            }
        }
        return orders;
    }

    public void addOrder(Order order) {
        orderDAO.insert(order);
    }

    public void updateOrder(Order order) {
        orderDAO.update(order);
    }

    public void deleteOrderById(int idOrder) {
        orderDAO.deleteById(idOrder);
    }

    public Order findOrderById(int idOrder) {
        Object obj = orderDAO.findById(idOrder);
        if (obj instanceof Order) {
            return (Order) obj;
        }
        return null;
    }
}
