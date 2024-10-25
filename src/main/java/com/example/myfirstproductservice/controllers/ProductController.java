package com.example.myfirstproductservice.controllers;

import com.example.myfirstproductservice.models.Product;
import com.example.myfirstproductservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getAProductById(@PathVariable("id") int id) {

        return productService.getProductById(id);
    }

    @PostMapping("")
    public Product addProduct(@RequestBody Product product) {

        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") int id, @RequestBody Product product) {

        return productService.replaceProduct(id, product);
    }

    @PatchMapping("/{id}")
    public void updateProduct(@PathVariable("id") int id, @RequestBody Product product) {

        return;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") int id) {

        productService.deleteProductById(id);
    }
}
