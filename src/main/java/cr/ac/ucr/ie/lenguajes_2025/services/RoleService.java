/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Role;
import cr.ac.ucr.ie.lenguajes_2025.repository.RoleRepository;
import java.util.LinkedList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Josías Morales
 */

@Service
public class RoleService {
   
     private final RoleRepository roleRepository;

     @Autowired
     public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
     
    // Obtener todos los roles
    public LinkedList<Role> getAllRoles() {
        return new LinkedList<>(roleRepository.findAll());
    }

    // Buscar un rol por su ID
    public Role findRoleById(int id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        return optionalRole.orElse(null);
    }

    // Buscar un rol por su nombre
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    // Insertar un nuevo rol
    public void insertRole(Role role) {
        roleRepository.save(role);
    }

    // Actualizar un rol existente
    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    // Eliminar un rol por su ID
    public void deleteRoleById(int id) {
        roleRepository.deleteById(id);
    }
}
