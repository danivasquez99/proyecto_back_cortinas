/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.dao;

import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;

/**
 *
 * @author Josías Morales
 */

public interface PermissionDAO extends CRUD<Permission>{
    Permission findByName(String name);
}
