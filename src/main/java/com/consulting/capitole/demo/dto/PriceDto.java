package com.consulting.capitole.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {
    private Long productId;
    private Long brandId;
    private Long priceId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
}
