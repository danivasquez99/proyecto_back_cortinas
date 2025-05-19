package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import cr.ac.ucr.ie.lenguajes_2025.dto.LoginRequest;
import cr.ac.ucr.ie.lenguajes_2025.services.UserService;
import java.util.Collections;
import java.util.Map;
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

/**
 *
 * @author Daniel
 */
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("users")
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

    @PutMapping("/edit")
    @ResponseBody
    public Map updateUser(@RequestBody User user) {
        UserService.updateUser(user);
        return getList();
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
