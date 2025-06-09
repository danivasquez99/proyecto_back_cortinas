package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {

    private final ProductoRepository productoRepository;
    private final ImageServiceJPA imageService;

    @Autowired
    public ProductServices(ProductoRepository productoRepository, ImageServiceJPA imageService) {
        this.productoRepository = productoRepository;
        this.imageService = imageService;
    }

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productoRepository.findAll();
    }

    // Insertar producto con imagen
    public Product insertProductWithImage(Product product, MultipartFile imageFile) throws Exception {
        Product savedProduct = productoRepository.save(product);

        // Guardar la imagen y obtener su URL
        String imageUrl = imageService.storeImage(savedProduct.getIdProduct(), imageFile);
        savedProduct.setImageUrl(imageUrl);

        return productoRepository.save(savedProduct);
    }

    public Product updateProductWithImage(Product product, MultipartFile imageFile) throws Exception {
        String imageUrl = imageService.storeImage(product.getIdProduct(), imageFile);
        product.setImageUrl(imageUrl);
        return productoRepository.save(product);
    }

    // Insertar sin imagen
    public Product insertProduct(Product product) {
        return productoRepository.save(product);
    }

    // Actualizar un producto existente
    public Product updateProduct(Product product) {
        return productoRepository.save(product);
    }

    // Eliminar un producto por su ID
    public void deleteProductById(int id) {
        productoRepository.deleteById(id);
    }

    // Buscar un producto por su ID
    public Optional<Product> findProductById(int id) {
        return productoRepository.findById(id);
    }

    public ImageServiceJPA getImageService() {
        return imageService;
    }
}
