package com.consulting.capitole.demo.service;

import com.consulting.capitole.demo.dto.PriceDto;
import com.consulting.capitole.demo.exception.PriceNotFoundException;
import com.consulting.capitole.demo.model.Brand;
import com.consulting.capitole.demo.model.Price;
import com.consulting.capitole.demo.model.Product;
import com.consulting.capitole.demo.repository.PriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PriceService subject;

    private final Long brandId = 1L;
    private final Long productId = 35455L;
    private final LocalDateTime purchaseDate = LocalDateTime.parse("2020-06-14T10:00:00");
    private final Brand brand = new Brand(brandId, "Zara");
    private final Product product = new Product(productId, brand, "Vestido estampado floral");
    private final Price price = new Price(1L, brand,
            LocalDateTime.parse("2020-06-14T00:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"),
            product, 0, BigDecimal.valueOf(35.50), "EUR");
    private final PriceDto priceDto = new PriceDto(productId, brandId, 1L,
            LocalDateTime.parse("2020-06-14T00:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"),
            BigDecimal.valueOf(35.50));

    @Test
    public void testGetPriceShouldReturn200AndPriceDto() {
        when(priceRepository.findPriceByBrandAndProductAndPurchaseDate(brandId, productId, purchaseDate))
                .thenReturn(price);
        when(modelMapper.map(price, PriceDto.class)).thenReturn(priceDto);

        PriceDto result = subject.getPrice(brandId, productId, purchaseDate);

        Assertions.assertEquals(brandId, result.getBrandId());
        Assertions.assertEquals(productId, result.getProductId());
        Assertions.assertEquals(priceDto.getPriceId(), result.getPriceId());
        Assertions.assertEquals(priceDto.getPrice(), result.getPrice());
        Assertions.assertEquals(priceDto.getStartDate(), result.getStartDate());
        Assertions.assertEquals(priceDto.getEndDate(), result.getEndDate());
    }

    @Test
    public void testGetPriceShouldReturn404() {
        when(priceRepository.findPriceByBrandAndProductAndPurchaseDate(brandId, productId, purchaseDate))
                .thenReturn(null);

        assertThatThrownBy(() -> subject.getPrice(brandId, productId, purchaseDate))
                .isInstanceOf(PriceNotFoundException.class);
    }
}
