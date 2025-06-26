/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.domain.ProductImage;
import cr.ac.ucr.ie.lenguajes_2025.dto.ProductDTO;
import cr.ac.ucr.ie.lenguajes_2025.mapper.ProductMapper;
import cr.ac.ucr.ie.lenguajes_2025.repository.ProductImageRepository;
import cr.ac.ucr.ie.lenguajes_2025.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Tony
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductImageRepository productImageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productImageRepository = productImageRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        // Para cada producto, buscar su imagen y asignar la URL al campo imageUrl
        products.forEach(product -> {
            Optional<ProductImage> imageOpt = productImageRepository.findByProduct(product);
            imageOpt.ifPresent(image -> product.setImageUrl(image.getUrlPath()));
        });

        return products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ProductDTO> getProductDTOById(int id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO);
    }

    public Optional<Product> getProductEntityById(int id) {
        return productRepository.findById(id);
    }

    public ProductDTO createProduct(Product product) {
        return productMapper.toDTO(productRepository.save(product));
    }

    public ProductDTO updateProduct(int id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedProduct.getName());
                    existing.setDetails(updatedProduct.getDetails());
                    existing.setPrice(updatedProduct.getPrice());
                    existing.setStock(updatedProduct.getStock());
                    existing.setImageUrl(updatedProduct.getImageUrl());
                    existing.setEntryDate(updatedProduct.getEntryDate());
                    return productMapper.toDTO(productRepository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Product updateProductEntity(Product updatedProduct) {
        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
