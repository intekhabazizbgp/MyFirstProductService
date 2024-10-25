package com.example.myfirstproductservice.services;

import com.example.myfirstproductservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product getProductById(int id);
    List<Product> getAllProducts();
    void deleteProductById(int id);
    Product addProduct(Product product);
    void updateProduct(int id, Product product);
    Product replaceProduct(int id, Product product);
}
