package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import cr.ac.ucr.ie.lenguajes_2025.repository.UserRepository;
import cr.ac.ucr.ie.lenguajes_2025.security.JwtUtils;
import cr.ac.ucr.ie.lenguajes_2025.security.SecurityUtils;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniel
 */
@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = SecurityUtils.encryptSHA256(loginData.get("password"));
        
        User user = userRepository.findByEmail(email);

        if (user == null || !(user.getPassword().equals(password))) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Credenciales inválidas");
            return ResponseEntity.status(401).body(response);
        }

        // Generar token usando email y nombre
        String token = jwtUtils.generateToken(user.getEmail(), user.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("email", user.getEmail());
        response.put("name", user.getName());
        response.put("id", user.getIdUser());
        response.put("urlProfilePicture", user.getUrlProfilePicture());
        response.put("role", user.getRole());
        return ResponseEntity.ok(response);
    }

}
