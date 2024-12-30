package com.ecommerce.productcatalogservice_application.dtos;

import com.ecommerce.productcatalogservice_application.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto
{
    private Long id;
    private String name;
    private String description;
}
