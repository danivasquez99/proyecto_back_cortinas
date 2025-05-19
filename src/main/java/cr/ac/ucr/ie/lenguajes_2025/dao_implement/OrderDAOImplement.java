package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.OrderDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.Order;

import java.sql.*;
import java.util.LinkedList;

public class OrderDAOImplement implements OrderDAO {

    @Override
    public LinkedList<Object> getAll() {
        LinkedList<Object> orderList = new LinkedList<>();
        String sql = "{CALL sp_get_all_order()}";

        try (Connection cn = ConnectionDB.getConnection();
             CallableStatement cs = cn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setIdOrder(rs.getInt("idOrder"));
                order.setStatus(rs.getString("status"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setEstimatedDeliveryDate(rs.getDate("estimatedDeliveryDate"));
                order.setTotal(rs.getFloat("total"));
                order.setCreatedAt(rs.getDate("created_at"));
                orderList.add(order);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener órdenes: " + e.getMessage());
        }

        return orderList;
    }

    @Override
    public void insert(Object t) {
        Order order = (Order) t;
        String sql = "{CALL sp_insert_order(?, ?, ?, ?)}";

        try (Connection cn = ConnectionDB.getConnection();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, order.getStatus());
            cs.setDate(2, order.getOrderDate());
            cs.setDate(3, order.getEstimatedDeliveryDate());
            cs.setFloat(4, order.getTotal());

            cs.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar orden: " + e.getMessage());
        }
    }

    @Override
    public void update(Object t) {
        Order order = (Order) t;
        String sql = "{CALL sp_update_order(?, ?, ?, ?, ?)}";

        try (Connection cn = ConnectionDB.getConnection();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, order.getIdOrder());
            cs.setString(2, order.getStatus());
            cs.setDate(3, order.getOrderDate());
            cs.setDate(4, order.getEstimatedDeliveryDate());
            cs.setFloat(5, order.getTotal());

            cs.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar orden: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idOrder) {
        String sql = "{CALL sp_delete_order_by_id(?)}";

        try (Connection cn = ConnectionDB.getConnection();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, idOrder);
            cs.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar orden: " + e.getMessage());
        }
    }

    @Override
    public Object findById(Integer idOrder) {
        Order order = null;
        String sql = "{CALL sp_find_order_by_id(?)}";

        try (Connection cn = ConnectionDB.getConnection();
             CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, idOrder);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setIdOrder(rs.getInt("idOrder"));
                order.setStatus(rs.getString("status"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setEstimatedDeliveryDate(rs.getDate("estimatedDeliveryDate"));
                order.setTotal(rs.getFloat("total"));
                order.setCreatedAt(rs.getDate("created_at"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar orden: " + e.getMessage());
        }

        return order;
    }
}
