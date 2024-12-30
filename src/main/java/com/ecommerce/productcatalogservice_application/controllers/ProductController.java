package com.ecommerce.productcatalogservice_application.controllers;

import com.ecommerce.productcatalogservice_application.dtos.CategoryDto;
import com.ecommerce.productcatalogservice_application.dtos.ProductDto;
import com.ecommerce.productcatalogservice_application.models.Product;
import com.ecommerce.productcatalogservice_application.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{
    @Autowired
    private IProductService productService;

    @Autowired
    private IProductService productService2;
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
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long productId) {
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            if (productId <= 0) {
                headers.add("called by", "bhudwak");
                //return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
                throw new IllegalArgumentException("Please try with productId > 0");
            }

            Product product = productService2.getProductById(productId);
            headers.add("called by", "intelligent");
            if (product == null) return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(from(product), headers, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            throw exception;
        }
    }

    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageURL(product.getImageURL());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
        }
        return productDto;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product)
    {
        return product;
    }

}
