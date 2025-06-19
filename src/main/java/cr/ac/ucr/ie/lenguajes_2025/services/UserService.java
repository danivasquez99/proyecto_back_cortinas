package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import cr.ac.ucr.ie.lenguajes_2025.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
@Service
public class UserService {
    
    private final UserRepository repo;
    private final UserImageService imageService;

    @Autowired
     public UserService(UserRepository repo, UserImageService imageService) {
         this.repo = repo;
         this.imageService = imageService;
     }
    
    public List<User> getAllUsers(){
        return repo.findAll();
    }
    
    public User getUserById(int id){
        return repo.findById(id).get();
    }
    
    public void insertUser(User newUser){
        repo.save(newUser);
    }
    
    public void updateUser(User modifyUser){
        repo.save(modifyUser);
    }
    
    public void deleteUser(int userId){
        repo.deleteById(userId);
    }
    
    public User login(String email, String password){
        return this.repo.findByEmailAndPassword(email, password);
    }
    
    public void insertUserWithImage(User user, MultipartFile imageFile) throws Exception {

        User lastInsertedUser = this.repo.save(user);

        String imageUrl = UserImageService.saveUserImage(imageFile);

        lastInsertedUser.setUrlProfilePicture(imageUrl);
        this.repo.save(user);
    }
    
    public void updateUserWithImage(User user, MultipartFile imageFile) throws Exception {
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = UserImageService.saveUserImage(imageFile);

            user.setUrlProfilePicture(imageUrl);
        } else {
            User existingUser = this.repo.findById(user.getIdUser()).get();
            user.setUrlProfilePicture(existingUser.getUrlProfilePicture());
            this.repo.save(user);
        }

    }
}
