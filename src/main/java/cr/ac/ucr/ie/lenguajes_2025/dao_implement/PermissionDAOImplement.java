/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.PermissionDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public class PermissionDAOImplement implements PermissionDAO {
    
    @Override
    public LinkedList<Permission> getAll() {
        LinkedList<Permission> permissions = new LinkedList<>();
        String sql = "SELECT id, name FROM permission";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Permission permission = new Permission();
                permission.setId(rs.getInt("id"));
                permission.setName(rs.getString("name"));
                permissions.add(permission);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener permisos: " + e.getMessage());
        }
        return permissions;
    }

    @Override
    public void insert(Permission permission) {
        String sql = "INSERT INTO permission (name) VALUES (?)";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, permission.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar permiso: " + e.getMessage());
        }
    }

    @Override
    public void update(Permission permission) {
        String sql = "UPDATE permission SET name = ? WHERE id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, permission.getName());
            ps.setInt(2, permission.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar permiso: " + e.getMessage());
        }
    }
    
    @Override
    public void deleteById(Integer id) {
        
        // Elimina relaciones antes de eliminar el permiso
        new RolePermissionDAOImplement().deleteByPermissionId(id);

        String sql = "DELETE FROM permission WHERE id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar permiso: " + e.getMessage());
        }
    }

    @Override
    public Permission findById(Integer id) {
        String sql = "SELECT id, name FROM permission WHERE id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Permission permission = new Permission();
                permission.setId(rs.getInt("id"));
                permission.setName(rs.getString("name"));
                return permission;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar permiso por id: " + e.getMessage());
        }
        
        return null;
    }
    
    public Permission findByName(String name) {
    String sql = "SELECT * FROM permission WHERE name = ?";
    
    try (Connection conn = ConnectionDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
       
        if (rs.next()) {
            Permission permission = new Permission();
            permission.setId(rs.getInt("id"));
            permission.setName(rs.getString("name"));
            
            return permission;
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar permiso por nombre: " + e.getMessage());
    }
    
        return null;
    }
}
