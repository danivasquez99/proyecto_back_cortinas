/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
import cr.ac.ucr.ie.lenguajes_2025.domain.Role;
import cr.ac.ucr.ie.lenguajes_2025.repository.PermissionRepository;
import cr.ac.ucr.ie.lenguajes_2025.repository.RoleRepository;
import java.util.LinkedList;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Josías Morales
 */

@Service
public class RolePermissionService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public RolePermissionService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }
    
    // 1. Obtener permisos de un rol
    public Set<Permission> getPermissionsByRoleId(int roleId) {
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        return role.getPermissions();
    }

    // 2. Obtener roles que tienen un permiso
    public Set<Role> getRolesByPermissionId(int permissionId) {
        Permission permission = permissionRepository.findById(permissionId)
            .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
        return permission.getRoles();
    }

    // 3. Asignar permiso a un rol
    public void assignPermissionToRole(int roleId, int permissionId) {
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Permission permission = permissionRepository.findById(permissionId)
            .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));

        role.getPermissions().add(permission);
        roleRepository.save(role);
    }
    
    public Role assignPermission(int roleId, Set<Permission> permissions) {
    Role role = roleRepository.findById(roleId)
        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

    role.setPermissions(permissions); // Sobrescribe los permisos actuales
    return roleRepository.save(role); // Guarda y retorna el rol actualizado
}


    // 4. Quitar permiso de un rol
    public void removePermissionFromRole(int roleId, int permissionId) {
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Permission permission = permissionRepository.findById(permissionId)
            .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));

        role.getPermissions().remove(permission);
        roleRepository.save(role);
    }
    
    public LinkedList<Permission> getPermissionsDetailsByRoleId(int roleId) {
    Role role = roleRepository.findById(roleId)
        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

    return new LinkedList<>(role.getPermissions());
  }
}
