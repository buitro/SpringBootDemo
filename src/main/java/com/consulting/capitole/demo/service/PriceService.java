package com.consulting.capitole.demo.service;

import com.consulting.capitole.demo.dto.PriceDto;
import com.consulting.capitole.demo.exception.PriceNotFoundException;
import com.consulting.capitole.demo.model.Price;
import com.consulting.capitole.demo.repository.PriceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PriceService {
    private PriceRepository priceRepository;
    private ModelMapper modelMapper;

    public PriceDto getPrice(Long brandId, Long productId, LocalDateTime purchaseDate) {
        Price price = priceRepository.findPriceByBrandAndProductAndPurchaseDate(brandId, productId, purchaseDate);
        if (price == null) {
            throw new PriceNotFoundException(
                    "brandId: " + brandId + " productId: " + productId + " purchaseDate: " + purchaseDate);
        }
        return modelMapper.map(price, PriceDto.class);
    }
}
