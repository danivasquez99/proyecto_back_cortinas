package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.ServiceDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.Service;

import java.sql.*;
import java.util.LinkedList;

public class ServiceDAOImplement implements ServiceDAO {

    private static final String SELECT_ALL = "SELECT * FROM service";
    private static final String INSERT = "INSERT INTO service (name, category, description, estimatedCost, estimatedDuration, status, created_at, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE service SET name=?, category=?, description=?, estimatedCost=?, estimatedDuration=?, status=?, imageUrl=? WHERE idService=?";
    private static final String DELETE = "DELETE FROM service WHERE idService=?";
    private static final String FIND_BY_ID = "SELECT * FROM service WHERE idService=?";

    @Override
    public LinkedList<Service> getAll() {
        LinkedList<Service> services = new LinkedList<>();

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Service s = new Service(
                        rs.getInt("idService"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("description"),
                        rs.getFloat("estimatedCost"),
                        rs.getString("estimatedDuration"),
                        rs.getString("status").charAt(0),
                        rs.getDate("created_at"),
                        rs.getString("imageUrl")
                );
                services.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error en getAll: " + e.getMessage());
        }

        return services;
    }

    @Override
    public void insert(Service s) {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setString(1, s.getName());
            ps.setString(2, s.getCategory());
            ps.setString(3, s.getDescription());
            ps.setFloat(4, s.getEstimatedCost());
            ps.setString(5, s.getEstimatedDuration());
            ps.setString(6, String.valueOf(s.getStatus()));
            ps.setDate(7, s.getCreationDate());
            ps.setString(8, s.getImageUrl());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en insert: " + e.getMessage());
        }
    }

    @Override
    public void update(Service s) {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {

            ps.setString(1, s.getName());
            ps.setString(2, s.getCategory());
            ps.setString(3, s.getDescription());
            ps.setFloat(4, s.getEstimatedCost());
            ps.setString(5, s.getEstimatedDuration());
            ps.setString(6, String.valueOf(s.getStatus()));
            ps.setString(7, s.getImageUrl());
            ps.setInt(8, s.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en update: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en deleteById: " + e.getMessage());
        }
    }

    @Override
    public Service findById(Integer id) {
        Service s = null;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s = new Service(
                            rs.getInt("idService"),
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getString("description"),
                            rs.getFloat("estimatedCost"),
                            rs.getString("estimatedDuration"),
                            rs.getString("status").charAt(0),
                            rs.getDate("created_at"),
                            rs.getString("imageUrl")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en findById: " + e.getMessage());
        }

        return s;
    }
}
