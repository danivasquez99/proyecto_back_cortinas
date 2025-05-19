/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.RoleDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.Role;
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
        String sql = "{CALL sp_get_all_roles()}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            
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
        String sql = "{CALL sp_insert_role(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, role.getName());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar rol: " + e.getMessage());
        }
    }
    
    @Override
    public void update(Role role) {
        String sql = "{CALL sp_update_role(?, ?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, role.getId());
            cs.setString(2, role.getName());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar rol: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "{CALL sp_delete_role(?)}";
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar rol: " + e.getMessage());
        }
    }
    
    @Override
    public Role findById(Integer id) {
        String sql = "{CALL sp_find_role_by_id(?)}";
        Role role = null;
        
        try (Connection conn = ConnectionDB.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
           
            if (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar rol por id: " + e.getMessage());
        }
        
        return role;
    }
    
    @Override
    public Role findByName(String name) {
    String sql = "{CALL sp_find_role_by_name(?)}";
    Role role = null;
    
    try (Connection conn = ConnectionDB.getConnection();
         CallableStatement cs = conn.prepareCall(sql)) {
        cs.setString(1, name);
        ResultSet rs = cs.executeQuery();
       
        if (rs.next()) {
            role = new Role();
            role.setId(rs.getInt("id"));
            role.setName(rs.getString("name"));
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar rol por nombre: " + e.getMessage());
    }
    
    return role;
    }
}
