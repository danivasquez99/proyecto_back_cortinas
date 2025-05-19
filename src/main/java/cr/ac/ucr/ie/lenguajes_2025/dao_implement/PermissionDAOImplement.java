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
        String sql = "{CALL sp_get_all_permissions()}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            
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
        String sql = "{CALL sp_insert_permission(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, permission.getName());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar permiso: " + e.getMessage());
        }
    }

     @Override
    public void update(Permission permission) {
        String sql = "{CALL sp_update_permission(?, ?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, permission.getId());
            cs.setString(2, permission.getName());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar permiso: " + e.getMessage());
        }
    }
    
    @Override
    public void deleteById(Integer id) {
        String sql = "{CALL sp_delete_permission(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar permiso: " + e.getMessage());
        }
    }

    @Override
    public Permission findById(Integer id) {
        String sql = "{CALL sp_find_permission_by_id(?)}";
        Permission permission = null;
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            
            if (rs.next()) {
                permission = new Permission();
                permission.setId(rs.getInt("id"));
                permission.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar permiso por id: " + e.getMessage());
        }
        
        return permission;
    }
    
    @Override
    public Permission findByName(String name) {
        String sql = "{CALL sp_find_permission_by_name(?)}";
        Permission permission = null;
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                permission = new Permission();
                permission.setId(rs.getInt("id"));
                permission.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar permiso por nombre: " + e.getMessage());
        }
        
        return permission;
    }
}
