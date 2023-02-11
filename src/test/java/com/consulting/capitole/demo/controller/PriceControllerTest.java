package com.consulting.capitole.demo.controller;

import com.consulting.capitole.demo.dto.PriceDto;
import com.consulting.capitole.demo.service.PriceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController subject;

    private final Long brandId = 1L;
    private final Long productId = 35455L;
    private final LocalDateTime purchaseDate = LocalDateTime.parse("2020-06-14T10:00:00");
    private final PriceDto expectedPrice = new PriceDto(productId, brandId, 1L,
            LocalDateTime.parse("2020-06-14T00:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"),
            BigDecimal.valueOf(35.50));

    @Test
    public void testGetPriceShouldReturn200AndPriceDto() {
        when(priceService.getPrice(brandId, productId, purchaseDate)).thenReturn(expectedPrice);

        ResponseEntity<PriceDto> responseEntity = subject.getPrice(brandId, productId, purchaseDate);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(brandId, responseEntity.getBody().getBrandId());
        Assertions.assertEquals(productId, responseEntity.getBody().getProductId());
        Assertions.assertEquals(expectedPrice.getPriceId(), responseEntity.getBody().getPriceId());
        Assertions.assertEquals(expectedPrice.getPrice(), responseEntity.getBody().getPrice());
        Assertions.assertEquals(expectedPrice.getStartDate(), responseEntity.getBody().getStartDate());
        Assertions.assertEquals(expectedPrice.getEndDate(), responseEntity.getBody().getEndDate());
    }
}
