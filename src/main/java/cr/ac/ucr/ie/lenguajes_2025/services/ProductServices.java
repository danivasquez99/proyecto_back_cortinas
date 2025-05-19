/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.ProductDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import java.util.LinkedList;

/**
 *
 * @author Tony
 */
public class ProductServices {

    // Instanciamos el DAO
    private static ProductDAOImplement productDAOImplement = new ProductDAOImplement();

    // Obtener todos los productos
    public LinkedList<Product> getAllProducts() {
        return productDAOImplement.getAll();
    }

    // Insertar un nuevo producto
    public void insertProduct(Product product) {
        productDAOImplement.insert(product);
    }

    // Actualizar un producto existente
    public void updateProduct(Product product) {
        productDAOImplement.update(product);
    }

    // Eliminar un producto por su ID
    public void deleteProductById(int id) {
        productDAOImplement.deleteById(id);
    }

    // Buscar un producto por su ID
    public Product findProductById(int id) {
        return productDAOImplement.findById(id);
    }
}
