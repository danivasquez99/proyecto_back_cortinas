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
        LinkedList<UserRole> userRoles = new LinkedList<>();
        String sql = "{CALL sp_get_all_user_roles()}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            
            while (rs.next()) {
                UserRole userRole = new UserRole();
                userRole.setUserId(rs.getInt("user_id"));
                userRole.setRoleId(rs.getInt("role_id"));
                userRoles.add(userRole);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los user_roles: " + e.getMessage());
        }
        
        return userRoles;
    }
    
    @Override
    public void insert(UserRole userRole) {
        String sql = "{CALL sp_insert_user_role(?, ?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, userRole.getUserId());
            cs.setInt(2, userRole.getRoleId());
            cs.executeUpdate();
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
    public LinkedList<UserRole> findByRoleId(Integer roleId) {
        LinkedList<UserRole> userRoles = new LinkedList<>();
        String sql = "{CALL sp_find_user_roles_by_role(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, roleId);
            ResultSet rs = cs.executeQuery();
           
            while (rs.next()) {
                UserRole userRole = new UserRole();
                userRole.setUserId(rs.getInt("user_id"));
                userRole.setRoleId(rs.getInt("role_id"));
                userRoles.add(userRole);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar user_roles por roleId: " + e.getMessage());
        }
        
        return userRoles;
    }

    @Override
    public LinkedList<UserRole> findByUserId(Integer userId) {
        LinkedList<UserRole> userRoles = new LinkedList<>();
        String sql = "{CALL sp_find_user_roles_by_user(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            
            while (rs.next()) {
                UserRole userRole = new UserRole();
                userRole.setUserId(rs.getInt("user_id"));
                userRole.setRoleId(rs.getInt("role_id"));
                userRoles.add(userRole);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar user_roles por userId: " + e.getMessage());
        }
        
        return userRoles;
    }

    @Override
    public void deleteByUserId(int userId) {
        String sql = "{CALL sp_delete_user_roles_by_user(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, userId);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar user_role por userId: " + e.getMessage());
        }
    }
    
    @Override
    public void deleteByRoleId(int roleId) {
        String sql = "{CALL sp_delete_user_roles_by_role(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, roleId);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar user_role por roleId: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer userId, Integer roleId) {
        String sql = "{CALL sp_delete_user_role(?, ?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, userId);
            cs.setInt(2, roleId);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar user_role: " + e.getMessage());
        }
    }
    
    // Verifica si ya existe la relación usuario-rol
    public boolean exists(int userId, int roleId) {
        String sql = "{CALL sp_exists_user_role(?, ?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, userId);
            cs.setInt(2, roleId);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de user_role: " + e.getMessage());
        }
        
        return false;
    }
}
