/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
import cr.ac.ucr.ie.lenguajes_2025.services.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

@RestController
@RequestMapping("/api/permission")
@CrossOrigin(origins = "http://localhost:3000")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
    
    // GET: Obtener todos los permisos
    @GetMapping("")
    public ResponseEntity<LinkedList<Permission>> getAllPermissions() {
        LinkedList<Permission> permissions = permissionService.getAllPermissions();
        if (permissions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(permissions);
    }

    // GET: Obtener un permiso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable int id) {
        Permission permission = permissionService.findPermissionById(id);
        return permission != null ? ResponseEntity.ok(permission) : ResponseEntity.notFound().build();
    }

    // GET: Obtener un permiso por nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<Permission> getPermissionByName(@PathVariable String name) {
        Permission permission = permissionService.findByName(name);
        return permission != null ? ResponseEntity.ok(permission) : ResponseEntity.notFound().build();
    }

    // POST: Crear un nuevo permiso
    @PostMapping("")
    public ResponseEntity<Void> createPermission(@RequestBody Permission permission) {
        permissionService.insertPermission(permission);
        return ResponseEntity.ok().build();
    }

    // PUT: Actualizar un permiso existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePermission(@PathVariable int id, @RequestBody Permission permission) {
        Permission existing = permissionService.findPermissionById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        permission.setId(id);
        permissionService.updatePermission(permission);
        return ResponseEntity.ok().build();
    }

    // DELETE: Eliminar un permiso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable int id) {
        Permission existing = permissionService.findPermissionById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        permissionService.deletePermissionById(id);
        return ResponseEntity.ok().build();
    }
}
