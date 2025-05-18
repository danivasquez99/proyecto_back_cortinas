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
        LinkedList<RolePermission> list = new LinkedList<>();
        String sql = "SELECT role_id, permission_id FROM role_permission";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                RolePermission RolePerm = new RolePermission();
                RolePerm.setRoleId(rs.getInt("role_id"));
                RolePerm.setPermissionId(rs.getInt("permission_id"));
                list.add(RolePerm);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener role_permission: " + e.getMessage());
        }
        
        return list;
    }

     @Override
    public void insert(RolePermission rolePermission) {
        
// Evita duplicados
        if (exists(rolePermission.getRoleId(), rolePermission.getPermissionId())) {
            System.out.println("La relación ya existe.");
            return;
        }
        
        String sql = "INSERT INTO role_permission (role_id, permission_id) VALUES (?, ?)";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rolePermission.getRoleId());
            ps.setInt(2, rolePermission.getPermissionId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar role_permission: " + e.getMessage());
        }
    }

    @Override
    public void update(RolePermission entity) {
        // No aplica porque normalmente no se actualizan claves compuestas
        throw new UnsupportedOperationException("Update no soportado para RolePermission.");
    }

    @Override
    public void deleteById(Integer id) {
        // No aplica porque la tabla tiene clave compuesta, no un solo id
        throw new UnsupportedOperationException("deleteById no soportado para RolePermission.");
    }
    
    @Override
    public RolePermission findById(Integer id) {
        // No aplica porque la tabla tiene clave compuesta, no un solo id
        return null;
    }
    
    @Override
    public LinkedList<RolePermission> findByRoleId(int roleId) {
        LinkedList<RolePermission> list = new LinkedList<>();
        String sql = "SELECT role_id, permission_id FROM role_permission WHERE role_id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                RolePermission RolePerm = new RolePermission();
                RolePerm.setRoleId(rs.getInt("role_id"));
                RolePerm.setPermissionId(rs.getInt("permission_id"));
                list.add(RolePerm);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar permisos por role_id: " + e.getMessage());
        }
        
         return list;
    }
    
    @Override
    public LinkedList<RolePermission> findByPermissionId(int permissionId) {
        LinkedList<RolePermission> list = new LinkedList<>();
        String sql = "SELECT role_id, permission_id FROM role_permission WHERE permission_id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, permissionId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                RolePermission RolePerm = new RolePermission();
                RolePerm.setRoleId(rs.getInt("role_id"));
                RolePerm.setPermissionId(rs.getInt("permission_id"));
                list.add(RolePerm);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar roles por permission_id: " + e.getMessage());
        }
        
        return list;
    }
    
    @Override
    public void deleteByRoleId(int roleId) {
    String sql = "DELETE FROM role_permission WHERE role_id = ?";
    
    try (Connection conn = ConnectionDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, roleId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Error deleting by roleId", e);
    }
}

@Override
public void deleteByPermissionId(int permissionId) {
    String sql = "DELETE FROM role_permission WHERE permission_id = ?";
    
    try (Connection conn = ConnectionDB.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, permissionId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Error deleting by permissionId", e);
    }
}

@Override
    public void delete(int roleId, int permissionId) {
        String sql = "DELETE FROM role_permission WHERE role_id = ? AND permission_id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.setInt(2, permissionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar role_permission: " + e.getMessage());
        }
    }
    
    // Verifica si ya existe la relación rol-permiso
    public boolean exists(int roleId, int permissionId) {
        String sql = "SELECT COUNT(*) FROM role_permission WHERE role_id = ? AND permission_id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.setInt(2, permissionId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de role_permission: " + e.getMessage());
        }
        
        return false;
    }
}
