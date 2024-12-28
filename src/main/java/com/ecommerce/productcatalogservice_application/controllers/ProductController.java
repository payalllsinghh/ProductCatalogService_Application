package com.ecommerce.productcatalogservice_application.controllers;

import com.ecommerce.productcatalogservice_application.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{
    @GetMapping
    public List<Product> getAllProducts()
    {
        Product product = new Product();
        product.setId(1L);
        product.setName("IPhone");
        List<Product> products = new ArrayList<Product>();
        products.add(product);
        return products;
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id)
    {
        Product product = new Product();
        product.setId(id);
        return product;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product)
    {
        return product;
    }

}
