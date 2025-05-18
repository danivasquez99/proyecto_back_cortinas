/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.dao;

import cr.ac.ucr.ie.lenguajes_2025.domain.Role;

/**
 *
 * @author Josías Morales
 */

public interface RoleDAO extends CRUD<Role>{
    Role findByName(String name);
}
