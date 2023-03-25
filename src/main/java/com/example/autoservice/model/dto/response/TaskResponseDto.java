package com.example.autoservice.model.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDto {
    private Long id;
    private Long orderId;
    private Long masterId;
    private BigDecimal price;
    private String status;
}
