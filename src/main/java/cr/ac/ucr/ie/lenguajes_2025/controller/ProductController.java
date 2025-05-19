/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.services.ProductServices;
import java.util.LinkedList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tony
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductServices productServices = new ProductServices();

    // Obtener todos los productos
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<LinkedList<Product>> getAllProducts() {
        LinkedList<Product> products = productServices.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    // Obtener un producto por su ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productServices.findProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // Insertar un nuevo producto
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        productServices.insertProduct(product);
        return ResponseEntity.created(null).build();
    }

    // Actualizar un producto
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setIdProduct(id);  // Aseguramos que el producto tiene el ID correcto
        productServices.updateProduct(product);
        return ResponseEntity.noContent().build();
    }

    // Eliminar un producto
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productServices.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
