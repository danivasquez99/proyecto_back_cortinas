package cr.ac.ucr.ie.lenguajes_2025.dao;

import cr.ac.ucr.ie.lenguajes_2025.domain.User;

/**
 *
 * @author Daniel
 */
public interface UserDAO extends CRUD<User>{
    
    public abstract boolean validateExistingEmail(String email);
    
    public abstract User login(String email, String password);
}
