/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
import cr.ac.ucr.ie.lenguajes_2025.domain.Role;
import cr.ac.ucr.ie.lenguajes_2025.services.PermissionService;
import cr.ac.ucr.ie.lenguajes_2025.services.RolePermissionService;
import cr.ac.ucr.ie.lenguajes_2025.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Josías Morales
 */

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {
    
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final RolePermissionService rolePermissionService;
    
    public RoleController(RoleService rolService, PermissionService permissionService, RolePermissionService rolePermissionService) {
        this.roleService = rolService;
        this.permissionService = permissionService;
        this.rolePermissionService = rolePermissionService;
    }

    // GET /api/roles - Obtener todos los roles
    @GetMapping
    public ResponseEntity<LinkedList<Role>> getAllRoles() {
        LinkedList<Role> roles = roleService.getAllRoles();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    // GET /api/roles/{id} - Obtener rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        Role role = roleService.findRoleById(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    // GET /api/roles/name/{name} - Obtener rol por nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        Role role = roleService.findByName(name);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }

    // POST /api/roles - Crear nuevo rol
    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody Role role) {
        roleService.insertRole(role);
        return ResponseEntity.ok().build();
    }

    // PUT /api/roles/{id} - Actualizar rol
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRole(@PathVariable int id, @RequestBody Role role) {
        Role existing = roleService.findRoleById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        role.setId(id);
        roleService.updateRole(role);
        return ResponseEntity.ok().build();
    }

    // DELETE /api/roles/{id} - Eliminar rol
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        Role existing = roleService.findRoleById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        roleService.deleteRoleById(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{rolId}/permissions")
    public ResponseEntity<Role> assignPermission(
        @PathVariable int rolId,
        @RequestBody Set<Integer> permissionsIds) {

    Set<Permission> permissions = permissionsIds.stream()
            .map(permissionService::findPermissionById)
            .filter(p -> p != null)
            .collect(Collectors.toSet());

    Role rolActualizado = rolePermissionService.assignPermission(rolId, permissions);
    return ResponseEntity.ok(rolActualizado);
  }
}
