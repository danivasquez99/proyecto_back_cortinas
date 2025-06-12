/*
 * Clase no va esta mal pensada ya que es para relacion muchos a muchos entre usuario y rol
 * y nosotros implementamos un rol por usuario
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.domain;

/**
 *
 * @author Josías Morales
 */

public class UserRole {
    private int userId;
    private int roleId;
    
    public UserRole() {}

    public UserRole(int userId, int roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" + "userId=" + userId + ", roleId=" + roleId + '}';
    }
}
