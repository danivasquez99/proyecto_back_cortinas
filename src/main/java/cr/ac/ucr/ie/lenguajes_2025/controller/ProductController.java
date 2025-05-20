package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import cr.ac.ucr.ie.lenguajes_2025.services.AuthService;
import cr.ac.ucr.ie.lenguajes_2025.services.ProductServices;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tony
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductServices productServices;
    private final AuthService authService;

    @Autowired
    public ProductController(ProductServices productServices, AuthService authService) {
        this.productServices = productServices;
        this.authService = authService;
    }

    // Obtener todos los productos (permiso: PRODUCT_VIEW)
    @GetMapping("")
    public LinkedList<Product> getAllProducts() {
        return productServices.getAllProducts();
    }

    // Obtener un producto por su ID (permiso: PRODUCT_VIEW)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable int id,
            @RequestParam String email,
            @RequestParam String password) {

        User user = authService.authenticate(email, password);
        if (!authService.hasPermission(user, "PRODUCT_VIEW")) {
            return ResponseEntity.status(403).build();
        }

        Product product = productServices.findProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // Insertar un nuevo producto (permiso: PRODUCT_CREATE)
    @PostMapping("")
    public ResponseEntity<Void> createProduct(
            @RequestBody Product product,
            @RequestParam String email,
            @RequestParam String password) {

        User user = authService.authenticate(email, password);
        if (!authService.hasPermission(user, "PRODUCT_CREATE")) {
            return ResponseEntity.status(403).build();
        }

        productServices.insertProduct(product);
        return ResponseEntity.created(null).build(); // Puedes incluir URI si tienes el ID generado
    }

    // Actualizar un producto (permiso: PRODUCT_UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable int id,
            @RequestBody Product product,
            @RequestParam String email,
            @RequestParam String password) {

        User user = authService.authenticate(email, password);
        if (!authService.hasPermission(user, "PRODUCT_UPDATE")) {
            return ResponseEntity.status(403).build();
        }

        product.setIdProduct(id);
        productServices.updateProduct(product);
        return ResponseEntity.noContent().build();
    }

    // Eliminar un producto (permiso: PRODUCT_DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable int id,
            @RequestParam String email,
            @RequestParam String password) {

        User user = authService.authenticate(email, password);
        if (!authService.hasPermission(user, "PRODUCT_DELETE")) {
            return ResponseEntity.status(403).build();
        }

        productServices.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/with-image")
    public ResponseEntity<Void> insertProductWithImage(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam(required = false, defaultValue = "0") Integer stock,
            @RequestParam("image") MultipartFile image,
            @RequestParam String email,
            @RequestParam String password) {
        try {
            // Autenticación y verificación de permisos
            User user = authService.authenticate(email, password);
            if (!authService.hasPermission(user, "PRODUCT_CREATE")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            // Crear producto
            Product product = new Product();
            product.setName(name);
            product.setDetails(description);
            product.setPrice((float) price);
            product.setStock(stock);

            try {
                // Usar el método existente que maneja la inserción completa
                productServices.insertProductWithImage(product, image);

                // Crear ubicación del recurso con URL absoluta
                String locationPath = "/api/products/" + product.getIdProduct();
                return ResponseEntity.status(HttpStatus.CREATED)
                        .header("Location", locationPath)
                        .build();

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
