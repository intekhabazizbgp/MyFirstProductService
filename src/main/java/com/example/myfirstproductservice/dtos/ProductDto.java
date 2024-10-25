package com.example.myfirstproductservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private int id;
    private String title;
    private double price;
    private String image;
    public String category;
    public String description;
}
