/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
import cr.ac.ucr.ie.lenguajes_2025.domain.RolePermission;
import cr.ac.ucr.ie.lenguajes_2025.services.RolePermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {
    
    private final RolePermissionService rolePermissionService = new RolePermissionService();


    // Obtener todas las asignaciones rol-permiso
    @GetMapping("")
    public ResponseEntity<LinkedList<RolePermission>> getAllRolePermissions() {
        LinkedList<RolePermission> list = rolePermissionService.getAllRolePermissions();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    // Obtener permisos por id de rol (solo IDs)
    @GetMapping("/role/{roleId}")
    public ResponseEntity<LinkedList<RolePermission>> getPermissionsByRoleId(@PathVariable int roleId) {
        LinkedList<RolePermission> list = rolePermissionService.getPermissionsByRoleId(roleId);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }
    
    // Obtener roles por id de permiso
    @GetMapping("/permission/{permissionId}")
    public ResponseEntity<LinkedList<RolePermission>> getRolesByPermissionId(@PathVariable int permissionId) {
        LinkedList<RolePermission> list = rolePermissionService.getRolesByPermissionId(permissionId);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    // Asignar permiso a rol
    @PostMapping("")
    public ResponseEntity<Void> assignPermissionToRole(@RequestBody RolePermission rolePermission) {
        rolePermissionService.assignPermissionToRole(rolePermission);
        return ResponseEntity.ok().build();
    }

    // Quitar permiso de rol
    @DeleteMapping("")
    public ResponseEntity<Void> removePermissionFromRole(@RequestParam int roleId, @RequestParam int permissionId) {
        rolePermissionService.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok().build();
    }

    // Obtener detalles de permisos por id de rol
    @GetMapping("/role/{roleId}/permissions")
    public ResponseEntity<LinkedList<Permission>> getPermissionsDetailsByRoleId(@PathVariable int roleId) {
        LinkedList<Permission> permissions = rolePermissionService.getPermissionsDetailsByRoleId(roleId);
        if (permissions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(permissions);
    }
}
