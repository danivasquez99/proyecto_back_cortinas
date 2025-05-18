/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Role;
import cr.ac.ucr.ie.lenguajes_2025.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    
    private final RoleService roleService = new RoleService();

    @GetMapping("")
    public ResponseEntity<LinkedList<Role>> getAllRoles() {
        LinkedList<Role> roles = roleService.getAllRoles();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        Role role = roleService.findRoleById(id);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(role);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        Role role = roleService.findByName(name);
    
        if (role == null) {
        return ResponseEntity.notFound().build();
    }
    
    return ResponseEntity.ok(role);
    }

    @PostMapping("")
    public ResponseEntity<Void> createRole(@RequestBody Role role) {
        roleService.insertRole(role);
        return ResponseEntity.ok().build();
    }

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
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        Role existing = roleService.findRoleById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        roleService.deleteRoleById(id);
        return ResponseEntity.ok().build();
    }
}
