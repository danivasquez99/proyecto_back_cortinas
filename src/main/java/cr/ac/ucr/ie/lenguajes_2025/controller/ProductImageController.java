package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.ImageServiceJDBC;
import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.services.ProductServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
public class ProductImageController {

    @Autowired
    private ImageServiceJDBC imageService;
    @Autowired
    private ProductServices productServices;

    @PostMapping("/{id}/images")
    public ResponseEntity<String> uploadProductImage(
            @PathVariable("id") int productId,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            String url = imageService.storeImage(productId, file);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error subiendo imagen: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<List<?>> listImages(@PathVariable("id") int productId) {
        try {
            return ResponseEntity.ok(imageService.listImagesByProduct(productId));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/{id}/images/full")
    public ResponseEntity<String> uploadImage(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = imageService.storeImage(id, file);

            // Actualizamos el campo imageUrl del producto
            Product product = productServices.findProductById(id);
            product.setImageUrl(imageUrl);
            productServices.updateProduct(product);

            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
