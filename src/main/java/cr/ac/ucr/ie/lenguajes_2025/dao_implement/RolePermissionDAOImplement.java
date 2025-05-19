/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.RolePermissionDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.RolePermission;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public class RolePermissionDAOImplement implements RolePermissionDAO{
    
    @Override
    public LinkedList<RolePermission> getAll() {
        LinkedList<RolePermission> rolePermissions = new LinkedList<>();
        String sql = "{CALL sp_get_all_role_permissions()}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            
            while (rs.next()) {
                RolePermission rp = new RolePermission();
                rp.setRoleId(rs.getInt("role_id"));
                rp.setPermissionId(rs.getInt("permission_id"));
                rolePermissions.add(rp);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los role_permissions: " + e.getMessage());
        }
        
        return rolePermissions;
    }

    @Override
    public void insert(RolePermission rolePermission) {
        String sql = "{CALL sp_insert_role_permission(?, ?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, rolePermission.getRoleId());
            cs.setInt(2, rolePermission.getPermissionId());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar role_permission: " + e.getMessage());
        }
    }

    @Override
    public void update(RolePermission entity) {
        // No aplica porque normalmente no se actualizan claves compuestas
        throw new UnsupportedOperationException("No soportado para RolePermission");
    }

    @Override
    public void deleteById(Integer id) {
        // No aplica porque la tabla tiene clave compuesta, no un solo id
        throw new UnsupportedOperationException("No soportado para RolePermission");
    }
    
    @Override
    public RolePermission findById(Integer id) {
        // No aplica porque la tabla tiene clave compuesta, no un solo id
        return null;
    }
    
    @Override
    public LinkedList<RolePermission> findByRoleId(Integer roleId) {
        LinkedList<RolePermission> rolePermissions = new LinkedList<>();
        String sql = "{CALL sp_find_role_permissions_by_role(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, roleId);
            ResultSet rs = cs.executeQuery();
            
            while (rs.next()) {
                RolePermission rp = new RolePermission();
                rp.setRoleId(rs.getInt("role_id"));
                rp.setPermissionId(rs.getInt("permission_id"));
                rolePermissions.add(rp);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar role_permissions por roleId: " + e.getMessage());
        }
        
        return rolePermissions;
    }
    
    @Override
    public LinkedList<RolePermission> findByPermissionId(Integer permissionId) {
        LinkedList<RolePermission> rolePermissions = new LinkedList<>();
        String sql = "{CALL sp_find_role_permissions_by_permission(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, permissionId);
            ResultSet rs = cs.executeQuery();
            
            while (rs.next()) {
                RolePermission rp = new RolePermission();
                rp.setRoleId(rs.getInt("role_id"));
                rp.setPermissionId(rs.getInt("permission_id"));
                rolePermissions.add(rp);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar role_permissions por permissionId: " + e.getMessage());
        }
        
        return rolePermissions;
    }
    
    @Override
    public void deleteByRoleId(int roleId) {
        String sql = "{CALL sp_delete_role_permissions_by_role(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, roleId);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar role_permissions por roleId: " + e.getMessage());
        }
    }

    @Override
    public void deleteByPermissionId(int permissionId) {
        String sql = "{CALL sp_delete_role_permissions_by_permission(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, permissionId);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar role_permissions por permissionId: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer roleId, Integer permissionId) {
        String sql = "{CALL sp_delete_role_permission(?, ?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, roleId);
            cs.setInt(2, permissionId);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar role_permission: " + e.getMessage());
        }
    }
    
    // Verifica si ya existe la relación rol-permiso
    public boolean exists(int roleId, int permissionId) {
        String sql = "{CALL sp_exists_role_permission(?, ?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, roleId);
            cs.setInt(2, permissionId);
            ResultSet rs = cs.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de role_permission: " + e.getMessage());
        }
        
        return false;
    }
}
