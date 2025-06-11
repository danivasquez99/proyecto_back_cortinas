package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.domain.ProductImage;
import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


@Service
public class ImageServiceJDBC {

    private final Path storageLocation;
    private final String imageBaseUrl;

    public ImageServiceJDBC(
        @Value("${app.images.dir:src/main/resources/static/images}") String imagesDir,
        @Value("${app.images.base-url:http://localhost:8080/images/}") String baseUrl
    ) throws IOException {
        this.storageLocation = Paths.get(imagesDir).toAbsolutePath().normalize();
        Files.createDirectories(this.storageLocation);
        this.imageBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    /**
     * Guarda el archivo en disco y persiste registro en product_image.
     * @param productId ID del producto al que pertenece
     * @param file      MultipartFile recibido
     * @return URL pública de la imagen
     */
    public String storeImage(int productId, MultipartFile file) throws Exception {
        // 1. Generar un nombre único usando timestamp + nombre original
        String original = file.getOriginalFilename();
        if (original == null) throw new IllegalArgumentException("Missing file name");
        String filename = System.currentTimeMillis() + "_" + original.replaceAll("\\s+", "_");
        Path target = storageLocation.resolve(filename);

        // 2. Guardar en disco
        try (InputStream in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }

        // 3. Construir URL
        String url = imageBaseUrl + filename;

        // 4. Insertar en BD
        String sql = "INSERT INTO product_image (product_id, file_name, url_path) VALUES (?, ?, ?)";
        try (
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, productId);
            ps.setString(2, filename);
            ps.setString(3, url);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Insert de imagen falló, no se afectó ninguna fila.");
            }
            // obtener idImage generado
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int idImage = keys.getInt(1);
                    // podemos devolver la URL, o un POJO si se desea
                    return url;
                } else {
                    throw new SQLException("Insert de imagen falló, no se obtuvo id.");
                }
            }
        }
    }

    /**
     * Recupera todas las imágenes asociadas a un producto.
     */
    public List<ProductImage> listImagesByProduct(int productId) throws Exception {
        String sql = "SELECT idImage, product_id, file_name, url_path, uploaded_at "
                   + "FROM product_image WHERE product_id = ?";
        List<ProductImage> images = new LinkedList<>();
        try (
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql)
        ) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProductImage img = new ProductImage();
                    img.setIdImage(rs.getInt("idImage"));
                    img.setProductId(rs.getInt("product_id"));
                    img.setFileName(rs.getString("file_name"));
                    img.setUrlPath(rs.getString("url_path"));
                    img.setUploadedAt(rs.getTimestamp("uploaded_at").toLocalDateTime());
                    images.add(img);
                }
            }
        }
        return images;
    }
    
    
}
