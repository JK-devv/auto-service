package com.example.autoservice.mapper;

public interface RequestMapper<M, D> {
    M mapToModel(D dto);
}
