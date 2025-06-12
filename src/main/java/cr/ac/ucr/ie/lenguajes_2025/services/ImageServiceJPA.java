package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.domain.ProductImage;
import cr.ac.ucr.ie.lenguajes_2025.repository.ProductImageRepository;
import cr.ac.ucr.ie.lenguajes_2025.services.ProductServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Service
public class ImageServiceJPA {

    private final Path storageLocation;
    private final String imageBaseUrl;
    private final ProductImageRepository productImageRepository;

    public ImageServiceJPA(
            ProductImageRepository productImageRepository,
            @Value("${app.images.dir:src/main/resources/static/images}") String imagesDir,
            @Value("${app.images.base-url:http://localhost:8080/images/}") String baseUrl
    ) throws Exception {
        this.productImageRepository = productImageRepository;
        this.storageLocation = Paths.get(imagesDir).toAbsolutePath().normalize();
        Files.createDirectories(this.storageLocation);
        this.imageBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    @Lazy
    @Autowired
    private ProductServices productServices;

    public String storeImage(int productId, MultipartFile file) throws Exception {
        String original = file.getOriginalFilename();
        if (original == null) {
            throw new IllegalArgumentException("Missing file name");
        }
        String filename = System.currentTimeMillis() + "_" + original.replaceAll("\\s+", "_");
        Path target = storageLocation.resolve(filename);

        try (InputStream in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }

        String url = imageBaseUrl + filename;

        Product product = productServices.findProductById(productId).orElseThrow(()
                -> new RuntimeException("Producto no encontrado con ID " + productId));

        if (product == null) {
            throw new IllegalArgumentException("Product not found for id " + productId);
        }

        ProductImage img = new ProductImage();
        img.setProduct(product); // <-- aquí asignas el objeto completo
        img.setFileName(filename);
        img.setUrlPath(url);
        img.setUploadedAt(LocalDateTime.now());

        productImageRepository.save(img);

        return url;
    }

    public List<ProductImage> listImagesByProduct(int productId) {
        return productImageRepository.findByProduct_IdProduct(productId);
    }
}
