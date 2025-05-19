package cr.ac.ucr.ie.lenguajes_2025.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Daniel
 */
public class Utils {

    public static String encryptSHA256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));

            StringBuilder builder = new StringBuilder();
            for (byte b : hashBytes) {
                builder.append(String.format("%02x", b));
            }

            return builder.toString().substring(0, 45); // Recortar a 45 caracteres
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

}
