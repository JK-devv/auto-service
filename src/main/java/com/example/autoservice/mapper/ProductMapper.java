package com.example.autoservice.mapper;

import com.example.autoservice.model.Product;
import com.example.autoservice.model.dto.request.ProductRequestDto;
import com.example.autoservice.model.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMapper implements RequestMapper<Product, ProductRequestDto>,
        ResponseMapper<Product, ProductResponseDto> {

    @Override
    public Product mapToModel(ProductRequestDto dto) {
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
    }

    @Override
    public ProductResponseDto mapToResponse(Product model) {
        return ProductResponseDto.builder()
                .id(model.getId())
                .name(model.getName())
                .price(model.getPrice())
                .build();
    }
}
