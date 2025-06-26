package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import cr.ac.ucr.ie.lenguajes_2025.services.UserService;
import java.sql.Date;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Map getList() {
        return Collections.singletonMap("data", userService.getAllUsers());
    }

    @GetMapping("/getById")
    public User findUserById(@RequestParam int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/insert")
    public Map insertUser(@RequestBody User user) {
        userService.insertUser(user);
        return getList();
    }

    @PutMapping("/update")
    public Map updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return getList();
    }

    @PostMapping("/create/with-image")
    public ResponseEntity<Void> insertUserWithImage(
            @RequestParam String name,
            @RequestParam Date birthdate,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            User user = new User();
            user.setName(name);
            user.setBirthdate(birthdate);
            user.setEmail(email);
            user.setPassword(password);

            try {
                userService.insertUserWithImage(user, image);

                String locationPath = "/api/users/" + user.getIdUser();
                return ResponseEntity.status(HttpStatus.CREATED)
                        .header("Location", locationPath)
                        .build();

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/with-image")
    public ResponseEntity<Void> updateUserWithImage(
            @RequestParam int idUser,
            @RequestParam String name,
            @RequestParam Date birthdate,
            @RequestParam String email,
            @RequestParam(required = false) String password,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            User user = new User();
            user.setIdUser(idUser);
            user.setName(name);
            user.setBirthdate(birthdate);
            user.setEmail(email);
            user.setPassword(password);

            // Servicio se encarga de actualizar imagen si viene una nueva
            userService.updateUserWithImage(user, image);

            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete")
    public Map deleteUser(@RequestParam int userId) {
        userService.deleteUser(userId);
        return getList();
    }

    @PostMapping("/login")
    @ResponseBody
    public User findUserByEmail(@RequestBody String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/existsByEmail")
    public ResponseEntity<Boolean> emailExists(@RequestParam String email) {
        User existingUser = userService.findUserByEmail(email);
        return ResponseEntity.ok(existingUser != null);
    }
}
