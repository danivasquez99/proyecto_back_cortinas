/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
import cr.ac.ucr.ie.lenguajes_2025.repository.PermissionRepository;
import java.util.LinkedList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Josías Morales
 */

@Service
public class PermissionService {
    
    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
    
    // Obtener todos los permisos
    public LinkedList<Permission> getAllPermissions() {
        return new LinkedList<>(permissionRepository.findAll());
    }

    // Buscar un permiso por su ID
    public Permission findPermissionById(int id) {
        Optional<Permission> optional = permissionRepository.findById(id);
        return optional.orElse(null);
    }

    // Buscar un permiso por su nombre
    public Permission findByName(String name) {
        return permissionRepository.findByName(name);
    }

    // Insertar un nuevo permiso
    public void insertPermission(Permission permission) {
        permissionRepository.save(permission);
    }

    // Actualizar un permiso existente
    public void updatePermission(Permission permission) {
        permissionRepository.save(permission);
    }

    // Eliminar un permiso por su ID
    public void deletePermissionById(int id) {
        permissionRepository.deleteById(id);
    }
}
