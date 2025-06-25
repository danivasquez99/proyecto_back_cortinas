package cr.ac.ucr.ie.lenguajes_2025.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

/**
 *
 * @author Daniel
 */
@Component
public class JwtUtils {

    // Clave secreta segura de al menos 256 bits (32 bytes)
    private final String SECRET_KEY = "49c3fbcde1a298f7e3c1bbdf3e8e9ad72f41cdef87b8a3cfb09df22efc8f1a3e";

    // Tiempo de expiración del token (10 horas)
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    // Convertimos el string a una clave HMAC-SHA válida
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Genera el JWT con claims: email y name
    public String generateToken(String email, String name) {
        return Jwts.builder()
                .setSubject(email)
                .claim("email", email)
                .claim("name", name)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extrae todos los claims
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Métodos auxiliares
    public String extractEmail(String token) {
        return extractClaims(token).get("email", String.class);
    }

    public String extractName(String token) {
        return extractClaims(token).get("name", String.class);
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
