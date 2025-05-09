package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.ServiceDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.Service;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Angel
 */
public class ServiceDAOImplement implements ServiceDAO {

    @Override
    public LinkedList<Service> getAll() {
        LinkedList<Service> services = new LinkedList<>();

        String sql = "SELECT id, name, category, description, estimatedCost, estimatedDuration, status, creationDate FROM service WHERE status = 'Available'";

        try (Connection cn = ConnectionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("name"));
                service.setCategory(rs.getString("category"));
                service.setDescription(rs.getString("description"));
                service.setEstimatedCost(rs.getFloat("estimatedCost"));
                service.setEstimatedDuration(rs.getString("estimatedDuration"));
                service.setStatus(rs.getString("status"));
                service.setCreationDate(rs.getDate("creationDate"));

                services.add(service);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los servicios: " + e.getMessage());
        }

        return services;
    }

    @Override
    public void insert(Service service) {
        String sql = "INSERT INTO service (id, name, category, description, estimatedCost, estimatedDuration, status, creationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConnectionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, service.getId());
            ps.setString(2, service.getName());
            ps.setString(3, service.getCategory());
            ps.setString(4, service.getDescription());
            ps.setFloat(5, service.getEstimatedCost());
            ps.setString(6, service.getEstimatedDuration());
            ps.setString(7, service.getStatus());
            ps.setDate(8, new Date(service.getCreationDate().getTime()));

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar servicio: " + e.getMessage());
        }
    }

    @Override
    public void update(Service service) {
        String sql = "UPDATE service SET name=?, category=?, description=?, estimatedCost=?, estimatedDuration=?, status=? WHERE id=?";

        try (Connection cn = ConnectionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, service.getName());
            ps.setString(2, service.getCategory());
            ps.setString(3, service.getDescription());
            ps.setFloat(4, service.getEstimatedCost());
            ps.setString(5, service.getEstimatedDuration());
            ps.setString(6, service.getStatus());
            ps.setInt(7, service.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar servicio: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "UPDATE service SET status='Not Available' WHERE id=?";

        try (Connection cn = ConnectionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar servicio: " + e.getMessage());
        }
    }

    @Override
    public Service findById(Integer id) {
        Service service = null;

        String sql = "SELECT id, name, category, description, estimatedCost, estimatedDuration, status, creationDate FROM service WHERE id=?";

        try (Connection cn = ConnectionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                service = new Service();
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("name"));
                service.setCategory(rs.getString("category"));
                service.setDescription(rs.getString("description"));
                service.setEstimatedCost(rs.getFloat("estimatedCost"));
                service.setEstimatedDuration(rs.getString("estimatedDuration"));
                service.setStatus(rs.getString("status"));
                service.setCreationDate(rs.getDate("creationDate"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar servicio: " + e.getMessage());
        }

        return service;
    }
}
