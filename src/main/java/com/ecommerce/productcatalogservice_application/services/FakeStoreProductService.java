package com.ecommerce.productcatalogservice_application.services;

import com.ecommerce.productcatalogservice_application.models.Category;
import com.ecommerce.productcatalogservice_application.models.Product;
import com.ecommerce.productcatalogservice_application.dtos.FakeStoreProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements IProductService
{
    @Autowired
    private IProductService productService;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public Product getProductById(Long productId)
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //restTemplate.rootUri("http://fakestoreapi.com/");
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("http://fakestoreapi.com/products/{productId}",FakeStoreProductDto.class,productId);

        if(fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) && fakeStoreProductDtoResponseEntity.getBody()!=null)
        {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }



        return null;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto)
    {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageURL(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
