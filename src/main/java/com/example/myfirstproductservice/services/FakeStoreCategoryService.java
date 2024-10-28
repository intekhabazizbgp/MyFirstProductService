package com.example.myfirstproductservice.services;

import com.example.myfirstproductservice.dtos.ProductDto;
import com.example.myfirstproductservice.models.Category;
import com.example.myfirstproductservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService{

    @Autowired
    private RestTemplate restTemplate;

    public FakeStoreCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Category> getAllCategories() {
        List<String> categoryList = List.of(restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class));

        List <Category> categories = new ArrayList<>();
        for (String category : categoryList) {

            categories.add(convertStringToCategory(category));
        }

        return categories;

    }

    @Override
    public List<Product> getAllProductsInCategory(String str) {
        List<ProductDto> productDtoList = List.of(restTemplate.getForObject("https://fakestoreapi.com/products/category/" +str, ProductDto[].class));
        List <Product> products = new ArrayList<>();
        for(ProductDto productDto : productDtoList){
            products.add(convertProductDtoToProduct(productDto));
        }

        return products;
    }

    public Category convertStringToCategory(String str) {

        Category category = new Category();
        category.setName(str);

        return category;
    }

    public Product convertProductDtoToProduct(ProductDto productDto) {

        Product product = new Product();

        product.setId(productDto.getId());
        product.setName(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());

        return product;
    }
}
