/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.repository;

import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author josia
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Permission findByName(String name);
}
