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
@RequestMapping("/api/permissions")
@CrossOrigin(origins = "http://localhost:3000")
public class PermissionController {
    
    private final PermissionService permissionService = new PermissionService();

    @GetMapping("")
    public ResponseEntity<LinkedList<Permission>> getAllPermissions() {
        LinkedList<Permission> permissions = permissionService.getAllPermissions();
        if (permissions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable int id) {
        Permission permission = permissionService.findPermissionById(id);
        if (permission == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(permission);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<Permission> getPermissionByName(@PathVariable String name) {
    Permission permission = permissionService.findByName(name);
    
    if (permission == null) {
        return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(permission);
}

    @PostMapping("")
    public ResponseEntity<Void> createPermission(@RequestBody Permission permission) {
        permissionService.insertPermission(permission);
        return ResponseEntity.ok().build();
    }
    
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
