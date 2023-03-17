package com.example.autoservice.service.impl;

import com.example.autoservice.model.Product;
import com.example.autoservice.repository.ProductRepository;
import com.example.autoservice.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public List<Product> findByIdIn(List<Long> productsId) {
        return repository.findByIdIn(productsId);
    }

    @Override
    public Product findById(Long productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Can`t find product by id: " + productId));
    }

    @Override
    public Product create(Product productRequestDto) {
        return repository.save(productRequestDto);
    }

    @Override
    public Product update(Long productId, Product productRequestDto) {
        Product existeProduct = repository.findById(productId)
                .orElseThrow(
                        () -> new RuntimeException("Can`t find product by id: " + productId));
        existeProduct.setPrice(productRequestDto.getPrice());
        existeProduct.setName(productRequestDto.getName());
        return repository.save(existeProduct);
    }
}
