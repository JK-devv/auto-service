package com.example.autoservice.mapper;

public interface ResponseMapper<M, D> {
    D mapToResponse(M model);
}
