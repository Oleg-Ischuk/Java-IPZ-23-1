package com.education.ztu.mockito;

import com.education.ztu.Product;
import java.util.List;

public interface ProductRepository {
    Product findById(String id);
    List<Product> findAll();
    void save(Product product);
}
