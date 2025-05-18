/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.UserRole;
import cr.ac.ucr.ie.lenguajes_2025.services.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {
    
    private final UserRoleService userRoleService = new UserRoleService();

    // Obtener todas las asignaciones usuario-rol
    @GetMapping("")
    public ResponseEntity<LinkedList<UserRole>> getAllUserRoles() {
        LinkedList<UserRole> list = userRoleService.getAllUserRoles();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    // Obtener roles por id de usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<LinkedList<UserRole>> getRolesByUserId(@PathVariable int userId) {
        LinkedList<UserRole> list = userRoleService.getRolesByUserId(userId);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    // Obtener usuarios por id de rol
    @GetMapping("/role/{roleId}")
    public ResponseEntity<LinkedList<UserRole>> getUsersByRoleId(@PathVariable int roleId) {
        LinkedList<UserRole> list = userRoleService.getUsersByRoleId(roleId);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    // Asignar rol a usuario
    @PostMapping("")
    public ResponseEntity<Void> assignRoleToUser(@RequestBody UserRole userRole) {
        userRoleService.assignRoleToUser(userRole);
        return ResponseEntity.ok().build();
    }

    // Quitar rol de usuario
    @DeleteMapping("")
    public ResponseEntity<Void> removeRoleFromUser(@RequestParam int userId, @RequestParam int roleId) {
        userRoleService.removeRoleFromUser(userId, roleId);
        return ResponseEntity.ok().build();
    }
}
