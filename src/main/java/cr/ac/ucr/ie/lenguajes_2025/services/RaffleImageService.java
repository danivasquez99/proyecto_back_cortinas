/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author josia
 */

@Service
public class RaffleImageService {
    private static Path storageLocation;
    private static String imageBaseUrl;

    @Autowired
    public RaffleImageService(
    @Value("${app.images.dir:src/main/resources/static/images/raffle}") String imagesDir,
    @Value("${app.images.base-url:http://localhost:8080/images/raffle/}") String baseUrl
) throws IOException {
    this.storageLocation = Paths.get(imagesDir).toAbsolutePath().normalize();
    Files.createDirectories(this.storageLocation);
    this.imageBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
}

    /**
    * Guarda la imagen y retorna la URL pública.
    */
    public static String saveRaffleImage(MultipartFile file) throws IOException {
    String original = file.getOriginalFilename();
    if (original == null || original.isBlank()) {
        throw new IllegalArgumentException("El archivo no tiene nombre.");
    }

    String filename = System.currentTimeMillis() + "_" + original.replaceAll("\\s+", "_");
    Path targetPath = storageLocation.resolve(filename);

    try (InputStream input = file.getInputStream()) {
        Files.copy(input, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    return imageBaseUrl + filename;
  }
}
