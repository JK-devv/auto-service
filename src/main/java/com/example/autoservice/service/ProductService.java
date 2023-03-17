package com.example.autoservice.service;

import com.example.autoservice.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> findByIdIn(List<Long> productsId);

    Product findById(Long productId);

    Product create(Product productRequestDto);

    Product update(Long productId, Product productRequestDto);
}
