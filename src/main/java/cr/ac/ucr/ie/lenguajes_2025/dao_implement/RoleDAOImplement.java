/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.RoleDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.Role;
import cr.ac.ucr.ie.lenguajes_2025.domain.UserRole;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public class RoleDAOImplement implements RoleDAO {
    
    @Override
    public LinkedList<Role> getAll() {
        LinkedList<Role> roles = new LinkedList<>();
        String sql = "SELECT id, name FROM role";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                roles.add(role);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener roles: " + e.getMessage());
        }
        
        return roles;
    }

    @Override
    public void insert(Role role) {
    String sql = "INSERT INTO role (name) VALUES (?)";
    
    try (Connection conn = ConnectionDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, role.getName());
        ps.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error al insertar rol: " + e.getMessage());
    }
}
    
    @Override
    public void update(Role role) {
        String sql = "UPDATE role SET name = ? WHERE id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, role.getName());
            ps.setInt(2, role.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar rol: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        
        // Elimina relaciones antes de eliminar el rol
        new RolePermissionDAOImplement().deleteByRoleId(id);
        new UserRoleDAOImplement().deleteByRoleId(id);

        String sql = "DELETE FROM role WHERE id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar rol: " + e.getMessage());
        }
    }
    
    @Override
    public Role findById(Integer id) {
        String sql = "SELECT id, name FROM role WHERE id = ?";
        
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                
                return role;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar rol por id: " + e.getMessage());
        }
        
        return null;
    }
    
    public Role findByName(String name) {
    String sql = "SELECT * FROM role WHERE name = ?";
    
    try (Connection conn = ConnectionDB.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
       
        if (rs.next()) {
            Role role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
          
            return role;
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar rol por nombre: " + e.getMessage());
    }
    
    return null;
}
}
