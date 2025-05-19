/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.PermissionDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public class PermissionService {
    
    // Instanciamos el DAO
    private static final PermissionDAOImplement permissionDAO = new PermissionDAOImplement();

    // Obtener todos los permisos
    public LinkedList<Permission> getAllPermissions() {
        return permissionDAO.getAll();
    }

    // Buscar un permiso por su ID
    public Permission findPermissionById(int id) {
        return permissionDAO.findById(id);
    }
    
    // Buscar un permiso por su nombre
    public Permission findByName(String name) {
        return permissionDAO.findByName(name);
    }

    // Insertar un nuevo permiso
    public void insertPermission(Permission permission) {
        permissionDAO.insert(permission);
    }

    // Actualizar un permiso existente
    public void updatePermission(Permission permission) {
        permissionDAO.update(permission);
    }

    // Eliminar un permiso por su ID
    public void deletePermissionById(int id) {
        permissionDAO.deleteById(id);
    }
}
