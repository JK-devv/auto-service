package com.example.autoservice.model.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarOwnerRequestDto {
    private List<Long> carsId;
    private List<Long> ordersId;
}
