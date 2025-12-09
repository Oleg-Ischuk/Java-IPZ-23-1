package com.education.ztu.mockito;

import com.education.ztu.Product;
import java.util.List;

public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product getProductById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        return repository.findById(id);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public boolean updateProductPrice(String id, double newPrice) {
        Product product = repository.findById(id);

        if (product != null) {
            product.setPrice(newPrice);
            repository.save(product);
            return true;
        }

        return false;
    }

    public void createNewProduct(Product product) {
        if (product == null) {
            throw new NullPointerException("Product cannot be null");
        }
        repository.save(product);
    }

    public boolean isProductAvailable(String id) {
        Product product = repository.findById(id);
        return product != null;
    }
}
