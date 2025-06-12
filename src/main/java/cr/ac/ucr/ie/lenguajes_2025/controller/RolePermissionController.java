/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
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
@CrossOrigin(origins = "http://localhost:3000")
public class RolePermissionController {
    
    private final RolePermissionService rolePermissionService;

    public RolePermissionController(RolePermissionService rolePermissionService) {
    this.rolePermissionService = rolePermissionService;
}

    // Para recibir los IDs de rol y permiso
    public static class RolePermissionDTO {
        public int roleId;
        public int permissionId;
    }

    // Asignar permiso a un rol
    @PostMapping("")
    public ResponseEntity<Void> assignPermissionToRole(@RequestBody RolePermissionDTO dto) {
        rolePermissionService.assignPermissionToRole(dto.roleId, dto.permissionId);
        return ResponseEntity.ok().build();
    }

    // Quitar permiso de un rol
    @DeleteMapping("")
    public ResponseEntity<Void> removePermissionFromRole(@RequestParam int roleId, @RequestParam int permissionId) {
        rolePermissionService.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok().build();
    }

    // Obtener detalles de permisos por ID de rol
    @GetMapping("/role/{roleId}/permissions")
    public ResponseEntity<LinkedList<Permission>> getPermissionsDetailsByRoleId(@PathVariable int roleId) {
        LinkedList<Permission> permissions = rolePermissionService.getPermissionsDetailsByRoleId(roleId);
        if (permissions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(permissions);
    }
}
