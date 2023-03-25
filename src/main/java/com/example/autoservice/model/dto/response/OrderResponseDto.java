package com.example.autoservice.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String description;
    private LocalDate dateCreated;
    private LocalDate dateCompleted;
    private List<Long> tasksId;
    private List<Long> productsId;
    private String status;
    private BigDecimal price;

}
