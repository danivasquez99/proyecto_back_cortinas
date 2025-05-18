/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.UserRoleDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.UserRole;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public class UserRoleDAOImplement implements UserRoleDAO {
    
    @Override
    public LinkedList<UserRole> getAll() {
        LinkedList<UserRole> list = new LinkedList<>();
        String sql = "SELECT user_id, role_id FROM user_role";
       
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
           
            while (rs.next()) {
                UserRole userRole = new UserRole();
                userRole.setUserId(rs.getInt("user_id"));
                userRole.setRoleId(rs.getInt("role_id"));
                list.add(userRole);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener user_role: " + e.getMessage());
        }
        
        return list;
    }
    
    @Override
    public void insert(UserRole userRole) {
        
        // Evita duplicados
        if (exists(userRole.getUserId(), userRole.getRoleId())) {
            System.out.println("La relación ya existe.");
            return;
        }
        
        String sql = "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userRole.getUserId());
            ps.setInt(2, userRole.getRoleId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar user_role: " + e.getMessage());
        }
    }

    // Método de la interfaz CRUD que no aplica
    @Override
    public void update(UserRole t) {
        throw new UnsupportedOperationException("No soportado.");
    }
    
    // Método de la interfaz CRUD que no aplica
    @Override
    public void deleteById(Integer t) {
        throw new UnsupportedOperationException("No soportado.");
    }
    // Método de la interfaz CRUD que no aplica
    @Override
    public UserRole findById(Integer t) {
        throw new UnsupportedOperationException("No soportado.");
    }
    
    @Override
    public LinkedList<UserRole> findByRoleId(int roleId) {
        LinkedList<UserRole> list = new LinkedList<>();
        String sql = "SELECT user_id, role_id FROM user_role WHERE role_id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                UserRole userRole = new UserRole();
                userRole.setUserId(rs.getInt("user_id"));
                userRole.setRoleId(rs.getInt("role_id"));
                list.add(userRole);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar usuarios por role_id: " + e.getMessage());
        }
        
        return list;
    }

    @Override
    public LinkedList<UserRole> findByUserId(int userId) {
        LinkedList<UserRole> list = new LinkedList<>();
        String sql = "SELECT user_id, role_id FROM user_role WHERE user_id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                UserRole userRole = new UserRole();
                userRole.setUserId(rs.getInt("user_id"));
                userRole.setRoleId(rs.getInt("role_id"));
                list.add(userRole);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar roles por user_id: " + e.getMessage());
        }
        
        return list;
    }

    @Override
    public void deleteByUserId(int userId) {
        String sql = "DELETE FROM user_role WHERE user_id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar user_role por userId: " + e.getMessage());
        }
    }
    
    @Override
    public void deleteByRoleId(int roleId) {
        String sql = "DELETE FROM user_role WHERE role_id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar user_role por roleId: " + e.getMessage());
        }
    }

    @Override
    public void delete(int userId, int roleId) {
        String sql = "DELETE FROM user_role WHERE user_id = ? AND role_id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar user_role: " + e.getMessage());
        }
    }
    
    // Verifica si ya existe la relación usuario-rol
    public boolean exists(int userId, int roleId) {
        String sql = "SELECT COUNT(*) FROM user_role WHERE user_id = ? AND role_id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de user_role: " + e.getMessage());
        }
        
        return false;
    }
}
