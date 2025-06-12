package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.UserDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import java.util.LinkedList;
import org.springframework.web.multipart.MultipartFile;

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
    
    public static void insertUserWithImage(User user, MultipartFile imageFile) throws Exception {
        service.insert(user);

        User lastInsertedUser = service.getLastInsertedUser();

        String imageUrl = UserImageService.saveUserImage(imageFile);

        lastInsertedUser.setUrlProfilePicture(imageUrl);
        service.update(lastInsertedUser);
    }
    
    public static void updateUserWithImage(User user, MultipartFile imageFile) throws Exception {
        if (imageFile != null && !imageFile.isEmpty()) {
            // 1. Guardar imagen nueva en disco
            String imageUrl = UserImageService.saveUserImage(imageFile);

            // 2. Asignar la nueva URL
            user.setUrlProfilePicture(imageUrl);
        } else {
            // 3. Mantener la imagen actual si no se sube una nueva
            User existingUser = service.findById(user.getIdUser());
            user.setUrlProfilePicture(existingUser.getUrlProfilePicture());
        }

        // 4. Actualizar promoción en DB
        service.update(user);
    }
}
