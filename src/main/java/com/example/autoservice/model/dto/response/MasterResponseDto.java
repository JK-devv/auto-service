package com.example.autoservice.model.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterResponseDto {
    private Long id;
    private String name;
    private List<Long> ordersId;
}
