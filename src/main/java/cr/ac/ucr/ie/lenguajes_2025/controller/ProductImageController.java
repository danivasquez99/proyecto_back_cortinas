package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.dto.ProductImageDTO;
import cr.ac.ucr.ie.lenguajes_2025.exception.ApiErrorResponse;
import cr.ac.ucr.ie.lenguajes_2025.services.ProductImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductImageController {

    private final ProductImageService productImageService;

    @Autowired
    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    // Obtener todas las imágenes
    @GetMapping
    public ResponseEntity<?> getAllImages(HttpServletRequest request) {
        try {
            List<ProductImageDTO> images = productImageService.getAllImages();
            if (images.isEmpty()) {
                return buildError(HttpStatus.NOT_FOUND, "No se encontraron imágenes registradas.", request.getRequestURI());
            }
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return buildError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI());
        }
    }

    // Obtener una imagen por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Integer id, HttpServletRequest request) {
        if (id == null || id <= 0) {
            return buildError(HttpStatus.BAD_REQUEST, "El ID debe ser mayor que 0.", request.getRequestURI());
        }

        Optional<ProductImageDTO> image = productImageService.getImageById(id);
        return image.<ResponseEntity<?>>map(ResponseEntity::ok).orElseGet(()
                -> buildError(HttpStatus.NOT_FOUND, "No se encontró una imagen con el ID proporcionado.", request.getRequestURI()));
    }

    // Obtener imagen por ID de producto
    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getImageByProductId(@PathVariable Integer productId, HttpServletRequest request) {
        if (productId == null || productId <= 0) {
            return buildError(HttpStatus.BAD_REQUEST, "El ID del producto debe ser mayor que 0.", request.getRequestURI());
        }

        return productImageService.getImageByProductId(productId)
                .<ResponseEntity<?>>map(ResponseEntity::ok).orElseGet(()
                -> buildError(HttpStatus.NOT_FOUND, "No se encontró una imagen asociada al producto.", request.getRequestURI()));
    }

    // Subir y asociar una imagen
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(
            @RequestParam("productId") Integer productId,
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        try {
            // Validaciones
            if (productId == null || productId <= 0) {
                return buildError(HttpStatus.BAD_REQUEST, "El ID del producto es inválido o no fue proporcionado.", request.getRequestURI());
            }
            if (file == null || file.isEmpty()) {
                return buildError(HttpStatus.BAD_REQUEST, "Debe proporcionar un archivo de imagen válido.", request.getRequestURI());
            }
            String originalName = file.getOriginalFilename();
            if (originalName == null || originalName.isBlank()) {
                return buildError(HttpStatus.BAD_REQUEST, "El archivo no tiene nombre válido.", request.getRequestURI());
            }
            if (!originalName.toLowerCase().matches(".*\\.(jpg|jpeg|png|webp|gif|bmp)$")) {
                return buildError(HttpStatus.BAD_REQUEST,
                        "Solo se permiten archivos de imagen (.jpg, .jpeg, .png, .webp, .gif, .bmp).",
                        request.getRequestURI());
            }
            long maxSizeBytes = 5 * 1024 * 1024;
            if (file.getSize() > maxSizeBytes) {
                return buildError(HttpStatus.BAD_REQUEST,
                        "El archivo supera el límite permitido de 5 MB.", request.getRequestURI());
            }

            String imageUrl = productImageService.storeImage(productId, file);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return buildError(HttpStatus.BAD_REQUEST, "Error al subir imagen: " + e.getMessage(), request.getRequestURI());
        }
    }

    // Eliminar imagen por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Integer id, HttpServletRequest request) {
        if (id == null || id <= 0) {
            return buildError(HttpStatus.BAD_REQUEST, "El ID debe ser mayor a 0.", request.getRequestURI());
        }

        try {
            productImageService.deleteImage(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar imagen: " + e.getMessage(), request.getRequestURI());
        }
    }

    // 🔧 Método para construir errores estructurados
    private ResponseEntity<ApiErrorResponse> buildError(HttpStatus status, String message, String path) {
        ApiErrorResponse error = new ApiErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
        return ResponseEntity.status(status).body(error);
    }
}
