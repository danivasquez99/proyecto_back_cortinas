/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.RoleDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.Role;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public class RoleService {
   
    // Instanciamos el DAO
    private static final RoleDAOImplement roleDAO = new RoleDAOImplement();

    // Obtener todos los roles
    public LinkedList<Role> getAllRoles() {
        return roleDAO.getAll();
    }

    // Buscar un role por su ID
    public Role findRoleById(int id) {
        return roleDAO.findById(id);
    }

    // Buscar un role por su nombre
    public Role findByName(String name) {
        return roleDAO.findByName(name);
    }

    // Insertar un nuevo role
    public void insertRole(Role role) {
        roleDAO.insert(role);
    }

    // Actualizar un role existente
    public void updateRole(Role role) {
        roleDAO.update(role);
    }

    // Eliminar un role por su ID
    public void deleteRoleById(int id) {
        roleDAO.deleteById(id);
    }
}
