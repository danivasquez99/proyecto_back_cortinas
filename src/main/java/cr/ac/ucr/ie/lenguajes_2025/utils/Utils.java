package cr.ac.ucr.ie.lenguajes_2025.utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    // Método para encriptar texto con SHA-256
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

    /* 
        Este metodo es para eliminar la direccion anterior a
        la imagen en caso de que se edite y cambie la imagen
        y la url
    
        Nota: aun no sirve
    */
    public static boolean deleteFile(String directory, String fileName) {
        if (fileName == null || fileName.isBlank()) return false;

        try {
            Path path = Paths.get(directory, fileName);
            return Files.deleteIfExists(path);
        } catch (Exception e) {
            System.err.println("No se pudo eliminar el archivo: " + e.getMessage());
            return false;
        }
    }
}
