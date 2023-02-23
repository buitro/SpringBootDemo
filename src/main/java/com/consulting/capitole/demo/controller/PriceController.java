package com.consulting.capitole.demo.controller;

import com.consulting.capitole.demo.dto.PriceDto;
import com.consulting.capitole.demo.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/v1")
public class PriceController {

    private PriceService priceService;

    @GetMapping(path = "/prices")
    public ResponseEntity<PriceDto> getPrice(
            @RequestParam Long brandId, @RequestParam Long productId, @RequestParam LocalDateTime purchaseDate) {
        PriceDto price = priceService.getPrice(brandId, productId, purchaseDate);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }
}
