package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.domain.ProductImage;
import cr.ac.ucr.ie.lenguajes_2025.dto.ProductImageDTO;
import cr.ac.ucr.ie.lenguajes_2025.mapper.ProductImageMapper;
import cr.ac.ucr.ie.lenguajes_2025.repository.ProductImageRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductImageMapper productImageMapper;
    private final Path storageLocation;
    private final String imageBaseUrl;

    @Lazy
    @Autowired
    private ProductService productService;

    @Autowired
    public ProductImageService(ProductImageRepository productImageRepository,
            ProductImageMapper productImageMapper,
            @Value("${app.images.dir:src/main/resources/static/images}") String imagesDir,
            @Value("${app.images.base-url:http://localhost:8080/images/}") String baseUrl) throws Exception {
        this.productImageRepository = productImageRepository;
        this.productImageMapper = productImageMapper;
        this.storageLocation = Paths.get(imagesDir).toAbsolutePath().normalize();
        Files.createDirectories(this.storageLocation);
        this.imageBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    public List<ProductImageDTO> getAllImages() {
        return productImageRepository.findAll().stream()
                .map(productImageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductImageDTO> getImageById(int id) {
        return productImageRepository.findById(id).map(productImageMapper::toDTO);
    }

    public Optional<ProductImageDTO> getImageByProductId(int productId) {
        return productService.getProductEntityById(productId)
                .flatMap(productImageRepository::findByProduct)
                .map(productImageMapper::toDTO);
    }

    public void deleteImage(int id) {
        Optional<ProductImage> optionalImage = productImageRepository.findById(id);

        if (optionalImage.isPresent()) {
            ProductImage image = optionalImage.get();
            boolean deleted = deleteFile(storageLocation.toString(), image.getUrlPath());

            if (!deleted) {
                throw new IllegalArgumentException("⚠️ No se pudo eliminar la imagen del disco para el archivo: " + image.getFileName());
            } else {
                System.out.println("✅ Imagen eliminada del sistema de archivos: " + image.getFileName());
            }

            productImageRepository.deleteById(id);
            System.out.println("✅ Registro de imagen eliminado de la base de datos con ID: " + id);
        } else {
            throw new IllegalArgumentException("⚠️ No se encontró ninguna imagen con ID: " + id);
        }
    }

    public String storeImage(int productId, MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("El archivo está vacío o no fue proporcionado.");
        }

        String originalName = file.getOriginalFilename();
        if (originalName == null || originalName.isBlank()) {
            throw new IllegalArgumentException("El archivo no tiene nombre válido.");
        }

        if (!originalName.toLowerCase().matches(".*\\.(jpg|jpeg|png|webp|gif|bmp)$")) {
            throw new IllegalArgumentException("Formato de archivo no soportado. Solo se permiten imágenes.");
        }

        long maxSizeBytes = 5 * 1024 * 1024; // 5 MB
        if (file.getSize() > maxSizeBytes) {
            throw new IllegalArgumentException("El tamaño del archivo excede el límite de 5 MB.");
        }

        String hashedName = encryptSHA256(originalName + System.currentTimeMillis()) + getFileExtension(originalName);
        Path targetLocation = storageLocation.resolve(hashedName);

        Product product = productService.getProductEntityById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID " + productId));

        // Eliminar imagen previa si existe (por relación OneToOne)
        productImageRepository.findByProduct(product).ifPresent(existing -> {
            deleteFile(storageLocation.toString(), existing.getFileName());
            productImageRepository.deleteById(existing.getIdImage());
        });

        try (InputStream in = file.getInputStream()) {
            Files.copy(in, targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Error al guardar la imagen en disco.", e);
        }

        String url = imageBaseUrl + hashedName;

        ProductImage image = new ProductImage();
        image.setProduct(product);
        image.setFileName(hashedName);
        image.setUrlPath(url);
        image.setUploadedAt(LocalDateTime.now());

        productImageRepository.save(image);

        // Actualizar URL en el producto
        product.setImageUrl(url);
        productService.updateProductEntity(product);

        return url;
    }

    public static String encryptSHA256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (byte b : hashBytes) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString().substring(0, 45);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No se encontró algoritmo SHA-256", e);
        }
    }

    private String getFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        return (lastDot != -1) ? filename.substring(lastDot) : "";
    }

    public static boolean deleteFile(String directory, String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return false;
        }
        try {
            Path path = Paths.get(directory).resolve(fileName).normalize();
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            System.err.println("No se pudo eliminar el archivo: " + e.getMessage());
            return false;
        }
    }

}
