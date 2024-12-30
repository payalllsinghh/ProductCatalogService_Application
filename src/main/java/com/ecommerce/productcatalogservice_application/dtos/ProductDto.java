package com.ecommerce.productcatalogservice_application.dtos;

import com.ecommerce.productcatalogservice_application.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto
{
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private Double price;
    private Category category;
    private Boolean isPrime;
}
