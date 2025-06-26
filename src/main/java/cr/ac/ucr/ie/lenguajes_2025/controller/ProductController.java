package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.dto.ProductDTO;
import cr.ac.ucr.ie.lenguajes_2025.services.ProductService;
import cr.ac.ucr.ie.lenguajes_2025.exception.ApiErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id, HttpServletRequest request) {
        return productService.getProductDTOById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> buildError(HttpStatus.NOT_FOUND, "Producto no encontrado", request.getRequestURI()));
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product, HttpServletRequest request) {
        try {
            validateProductDataCreate(product);
            ProductDTO created = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return buildError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI());
        }
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct, HttpServletRequest request) {
        try {
            validateProductDataUpdate(updatedProduct);
            ProductDTO updated = productService.updateProduct(id, updatedProduct);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return buildError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI());
        } catch (RuntimeException e) {
            return buildError(HttpStatus.NOT_FOUND, "Producto no encontrado para actualizar", request.getRequestURI());
        }
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id, HttpServletRequest request) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return buildError(HttpStatus.NOT_FOUND, "Producto no encontrado para eliminar", request.getRequestURI());
        }
    }

    // 🔧 Método reutilizable para construir errores con ApiErrorResponse
    private ResponseEntity<ApiErrorResponse> buildError(HttpStatus status, String message, String path) {
        ApiErrorResponse error = new ApiErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                path
        );
        return ResponseEntity.status(status).body(error);
    }

    // ✅ Validaciones de creación
    private void validateProductDataCreate(Product product) {
        LocalDate today = LocalDate.now();

        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("El precio debe ser un número positivo válido en colones CR.");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("La cantidad en stock no puede ser negativa.");
        }

        LocalDate entryDate = product.getEntryDate().toLocalDate();

        if (entryDate == null) {
            throw new IllegalArgumentException("La fecha de ingreso no puede estar vacía.");
        }

        if (entryDate.isBefore(today.minusMonths(1))) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser anterior a 1 mes desde hoy.");
        }

        if (entryDate.isAfter(today.plusMonths(18))) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser más de 1 año y medio en el futuro.");
        }
    }

    // ✅ Validaciones de edición
    private void validateProductDataUpdate(Product product) {
        LocalDate today = LocalDate.now();

        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("El precio debe ser un número positivo válido en colones CR.");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("La cantidad en stock no puede ser negativa.");
        }

        LocalDate entryDate = product.getEntryDate().toLocalDate();

        if (entryDate == null) {
            throw new IllegalArgumentException("La fecha de ingreso no puede estar vacía.");
        }

        if (entryDate.isAfter(today.plusMonths(3))) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser más de 3 meses en el futuro desde hoy.");
        }
    }
}
