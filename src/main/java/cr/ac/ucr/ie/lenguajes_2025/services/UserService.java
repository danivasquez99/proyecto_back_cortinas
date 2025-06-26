package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import cr.ac.ucr.ie.lenguajes_2025.repository.UserRepository;
import cr.ac.ucr.ie.lenguajes_2025.security.SecurityUtils;
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

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserById(int id) {
        return repo.findById(id).get();
    }

    public void insertUser(User newUser) {
        String encryptedPassword = SecurityUtils.encryptSHA256(newUser.getPassword());
        newUser.setPassword(encryptedPassword);
        repo.save(newUser);
    }

    public void deleteUser(int userId) {
        repo.deleteById(userId);
    }

    public User findUserByEmail(String email) {
        return this.repo.findByEmail(email);
    }

    public void insertUserWithImage(User user, MultipartFile imageFile) throws Exception {
        String encryptedPassword = SecurityUtils.encryptSHA256(user.getPassword());
        user.setPassword(encryptedPassword);

        User lastInsertedUser = this.repo.save(user);

        String imageUrl = UserImageService.saveUserImage(imageFile);

        lastInsertedUser.setUrlProfilePicture(imageUrl);
        this.repo.save(user);
    }

    public void updateUser(User user) {
        User existingUser = repo.findById(user.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Mantener la contraseña si no se envía
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            user.setPassword(existingUser.getPassword());
        } else {
            user.setPassword(SecurityUtils.encryptSHA256(user.getPassword()));
        }

        // Mantener la URL de la imagen si no se envía en la petición
        if (user.getUrlProfilePicture() == null || user.getUrlProfilePicture().isEmpty()) {
            user.setUrlProfilePicture(existingUser.getUrlProfilePicture());
        }

        repo.save(user);
    }

    public void updateUserWithImage(User user, MultipartFile imageFile) throws Exception {
        User existingUser = this.repo.findById(user.getIdUser()).get();

        // Mantener contraseña actual si no se envía una nueva
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            user.setPassword(existingUser.getPassword());
        } else {
            user.setPassword(SecurityUtils.encryptSHA256(user.getPassword()));
        }

        // Manejar imagen
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = UserImageService.saveUserImage(imageFile);
            user.setUrlProfilePicture(imageUrl);
        } else {
            user.setUrlProfilePicture(existingUser.getUrlProfilePicture());
        }

        repo.save(user);
    }

}
