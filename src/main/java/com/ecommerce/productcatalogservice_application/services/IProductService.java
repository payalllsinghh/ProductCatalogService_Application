package com.ecommerce.productcatalogservice_application.services;
import com.ecommerce.productcatalogservice_application.models.Product;
public interface IProductService
{
    Product getProductById(Long productId);
}
