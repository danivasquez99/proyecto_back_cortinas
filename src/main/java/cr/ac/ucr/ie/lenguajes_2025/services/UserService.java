package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.UserDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Daniel
 */
public class UserService {
    
    private static UserDAOImplement service = new UserDAOImplement();

    public UserService() {}
    
    public static LinkedList<User> getAllUsers(){
        return service.getAll();
    }
    
    public static User getUserById(int id){
        return service.findById(id);
    }
    
    public static void insertUser(User newUser){
        service.insert(newUser);
    }
    
    public static void updateUser(User modifyUser){
        service.update(modifyUser);
    }
    
    public static void deleteUser(int userId){
        service.deleteById(userId);
    }
    
    public static User login(String email, String password){
        return service.login(email, password);
    }
}
