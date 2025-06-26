package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.services.ProductServices;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductServices productServices;

    @Autowired
    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    // Obtener todos los productos
    @GetMapping("")
    public List<Product> getAllProducts() {
        return productServices.getAllProducts();
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> productOpt = productServices.findProductById(id);
        return productOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Insertar un nuevo producto sin imagen
    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saved = productServices.insertProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Actualizar un producto existente sin imagen
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setIdProduct(id);
        Product updated = productServices.updateProduct(product);
        return ResponseEntity.ok(updated);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productServices.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    // Insertar un producto con imagen
    @PostMapping("/with-image")
    public ResponseEntity<Product> insertProductWithImage(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam(required = false, defaultValue = "0") Integer stock,
            @RequestParam String entryDate, // <-- formato dd-MM-yyyy
            @RequestParam("image") MultipartFile image) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setDetails(description);
            product.setPrice((float) price);
            product.setStock(stock);

            // Parsear string a Date con formato dd-MM-yyyy
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = formatter.parse(entryDate);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            product.setEntryDate(sqlDate); // <-- setear la fecha convertida

            Product savedProduct = productServices.insertProductWithImage(product, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Actualizar un producto con imagen
    @PostMapping("/with-image-update")
    public ResponseEntity<Product> updateProductWithImage(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam(required = false, defaultValue = "0") Integer stock,
            @RequestParam("image") MultipartFile image) {
        try {
            Product product = new Product();
            product.setIdProduct(id);
            product.setName(name);
            product.setDetails(description);
            product.setPrice((float) price);
            product.setStock(stock);

            Product updatedProduct = productServices.updateProductWithImage(product, image);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
