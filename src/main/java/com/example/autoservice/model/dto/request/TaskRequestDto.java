package com.example.autoservice.model.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDto {
    private Long orderId;
    private Long masterId;
    private BigDecimal price;
    private String status;
}
