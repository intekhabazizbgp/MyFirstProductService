package com.example.myfirstproductservice.services;

import com.example.myfirstproductservice.dtos.ProductDto;
import com.example.myfirstproductservice.models.Category;
import com.example.myfirstproductservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestTemplateAutoConfiguration restTemplateAutoConfiguration;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(int id) {

        ProductDto productDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, ProductDto.class);
        return convertProductDtoToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductDto> productDtoList = List.of(restTemplate.getForObject("https://fakestoreapi.com/products", ProductDto[].class));

        List<Product> productList = new ArrayList<>();
        for(ProductDto productDto : productDtoList){
            productList.add(convertProductDtoToProduct(productDto));
        }
        return productList;
    }

    @Override
    public void deleteProductById(int id) {

        restTemplate.delete("https://fakestoreapi.com/products/" +id);
    }

    @Override
    public Product addProduct(Product product) {

        ProductDto productDto = convertProductToProductDto(product);
        ProductDto productDto1 = restTemplate.postForObject("https://fakestoreapi.com/products", productDto, ProductDto.class);
        return convertProductDtoToProduct(productDto1);
    }

    @Override
    public void updateProduct(int id, Product product) {

    }

    @Override
    public Product replaceProduct(int id, Product product) {

        ProductDto productDto = convertProductToProductDto(product);
        ProductDto productDto1 = restTemplate.patchForObject("https://fakestoreapi.com/products/" +id, product, ProductDto.class);
        return convertProductDtoToProduct(productDto1);
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

    public ProductDto convertProductToProductDto(Product product) {

        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setTitle(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImageUrl());
        productDto.setCategory(product.getCategory().getName());

        return productDto;
    }
}
