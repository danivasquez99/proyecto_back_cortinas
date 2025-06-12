package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.services.ImageServiceJPA;
import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductImageController {

    private final ImageServiceJPA imageService;

    private final ProductServices productServices;

    @Autowired
    public ProductImageController(ImageServiceJPA imageService, ProductServices productServices) {
        this.imageService = imageService;
        this.productServices = productServices;
    }

    // Subir imagen asociada a un producto
    @PostMapping("/{id}/images")
    public ResponseEntity<String> uploadProductImage(
            @PathVariable("id") int productId,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            // Guarda la imagen y retorna URL pública
            String url = imageService.storeImage(productId, file);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error subiendo imagen: " + e.getMessage());
        }
    }

    // Listar todas las imágenes de un producto
    @GetMapping("/{id}/images")
    public ResponseEntity<List<?>> listImages(@PathVariable("id") int productId) {
        try {
            List<?> images = imageService.listImagesByProduct(productId);
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    // Subir imagen y actualizar la URL en el producto (más completa)
    @PostMapping("/{id}/images/full")
    public ResponseEntity<String> uploadImageAndUpdateProduct(
            @PathVariable int id,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            String imageUrl = imageService.storeImage(id, file);

            // Buscar el producto con JPA
            Product product = productServices.findProductById(id).orElseThrow(()
                    -> new RuntimeException("Producto no encontrado con ID " + id));

            // Actualizar URL y guardar producto
            product.setImageUrl(imageUrl);
            productServices.updateProduct(product);

            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
