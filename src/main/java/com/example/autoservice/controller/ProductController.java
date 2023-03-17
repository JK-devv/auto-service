package com.example.autoservice.controller;

import com.example.autoservice.mapper.ProductMapper;
import com.example.autoservice.model.dto.request.ProductRequestDto;
import com.example.autoservice.model.dto.response.ProductResponseDto;
import com.example.autoservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper;

    @PostMapping("/create")
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        return mapper.mapToResponse(
                productService.create(
                        mapper.mapToModel(productRequestDto)));
    }

    @PutMapping("/update/{productId}")
    public ProductResponseDto update(@PathVariable Long productId,
                                     @RequestBody ProductRequestDto productRequestDto) {
        return mapper.mapToResponse(
                productService.update(
                        productId, mapper.mapToModel(productRequestDto)));
    }
}
