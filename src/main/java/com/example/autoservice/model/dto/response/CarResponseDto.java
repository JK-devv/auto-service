package com.example.autoservice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarResponseDto {
    private Long id;
    private String mark;
    private String model;
    private String year;
    private String number;
    private Long carOwnerId;
}
