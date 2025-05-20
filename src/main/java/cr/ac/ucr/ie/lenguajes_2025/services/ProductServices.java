package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.ProductDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.dao_implement.ImageServiceJDBC;
import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.LinkedList;

@Service
public class ProductServices {

    private final ProductDAOImplement productDAOImplement = new ProductDAOImplement();
    private final ImageServiceJDBC imageService;

    public ProductServices(ImageServiceJDBC imageService) {
        this.imageService = imageService;
    }

    // Obtener todos los productos
    public LinkedList<Product> getAllProducts() {
        return productDAOImplement.getAll();
    }

    // Insertar producto con imagen
    public void insertProductWithImage(Product product, MultipartFile imageFile) throws Exception {
        // 1. Insertar el producto primero sin imagen
        productDAOImplement.insert(product);

        //seter el ultimo producto
        product = productDAOImplement.getLastInsertedProduct();
        // 2. Guardar la imagen en disco y obtener la URL
        String imageUrl = imageService.storeImage(product.getIdProduct(), imageFile);

        // 3. Asignar la URL al producto
        product.setImageUrl(imageUrl);

        // 4. Actualizar el producto con la URL
        productDAOImplement.update(product);
    }

    // Insertar sin imagen (opcional)
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

    // Getter para acceder al servicio de imágenes
    public ImageServiceJDBC getImageService() {
        return imageService;
    }
}
