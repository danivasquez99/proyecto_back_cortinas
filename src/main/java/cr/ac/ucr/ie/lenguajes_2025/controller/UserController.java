package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import cr.ac.ucr.ie.lenguajes_2025.dto.LoginRequest;
import cr.ac.ucr.ie.lenguajes_2025.services.UserService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/users")
public class UserController {

    @GetMapping("/list")
    @ResponseBody
    public Map getList() {
        return Collections.singletonMap("data", UserService.getAllUsers());
    }

    @GetMapping("/getById")
    @ResponseBody
    public User findUserById(@RequestParam int userId) {
        return UserService.getUserById(userId);
    }

    @PostMapping("/insert")
    @ResponseBody
    public Map insertUser(@RequestBody User user) {
        UserService.insertUser(user);
        return getList();
    }

    @PutMapping("/update")
    @ResponseBody
    public Map updateUser(@RequestBody User user) {
        UserService.updateUser(user);
        return getList();
    }

    @PostMapping("/create/with-image")
    public ResponseEntity<Void> insertUserWithImage(
            @RequestParam String name,
            @RequestParam LocalDate birthdate,
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
                UserService.insertUserWithImage(user, image);

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
            @RequestParam LocalDate birthdate,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            User user = new User();
            user.setIdUser(idUser);
            user.setName(name);
            user.setBirthdate(birthdate);
            user.setEmail(email);
            user.setPassword(password);

            // Servicio se encarga de actualizar imagen si viene una nueva
            UserService.updateUserWithImage(user, image);

            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Map deleteUser(@RequestParam int userId) {
        UserService.deleteUser(userId);
        return getList();
    }

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        return UserService.login(email, password);
    }
}
