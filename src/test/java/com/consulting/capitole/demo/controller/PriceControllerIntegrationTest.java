package com.consulting.capitole.demo.controller;

import com.consulting.capitole.demo.SpringbootApplication;
import com.consulting.capitole.demo.dto.PriceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PriceControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String uri = "http://localhost:%s/v1/price?brandId=%s&productId=%s&purchaseDate=%s";
    private final Long brandId = 1L;
    private final Long productId = 35455L;

    @Test
    public void test1_Day14At10AM_ShouldReturnPrice1() {
        String endpoint = String.format(uri, port, brandId, productId, "2020-06-14T10:00:00");
        PriceDto price = restTemplate.getForObject(endpoint, PriceDto.class);
        
        assertEquals(Long.valueOf(1), price.getPriceId());
        assertEquals(brandId, price.getBrandId());
        assertEquals(productId, price.getProductId());
        assertEquals(new BigDecimal("35.50"), price.getPrice());
        assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), price.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), price.getEndDate());
    }


    @Test
    public void test2_Day14At16PM_ShouldReturnPrice2() {
        String endpoint = String.format(uri, port, brandId, productId, "2020-06-14T16:00:00");
        PriceDto price = restTemplate.getForObject(endpoint, PriceDto.class);
        
        assertEquals(Long.valueOf(2), price.getPriceId());
        assertEquals(brandId, price.getBrandId());
        assertEquals(productId, price.getProductId());
        assertEquals(new BigDecimal("25.45"), price.getPrice());
        assertEquals(LocalDateTime.parse("2020-06-14T15:00:00"), price.getStartDate());
        assertEquals(LocalDateTime.parse("2020-06-14T18:30:00"), price.getEndDate());
    }

    @Test
    public void test3_Day14At21PM_ShouldReturnPrice1() {
        String endpoint = String.format(uri, port, brandId, productId, "2020-06-14T21:00:00");
        PriceDto price = restTemplate.getForObject(endpoint, PriceDto.class);
        
        assertEquals(Long.valueOf(1), price.getPriceId());
        assertEquals(brandId, price.getBrandId());
        assertEquals(productId, price.getProductId());
        assertEquals(new BigDecimal("35.50"), price.getPrice());
        assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), price.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), price.getEndDate());
    }

    @Test
    public void test4_Day15At10AM_ShouldReturnPrice3() {
        String endpoint = String.format(uri, port, brandId, productId, "2020-06-15T10:00:00");
        PriceDto price = restTemplate.getForObject(endpoint, PriceDto.class);
        
        assertEquals(Long.valueOf(3), price.getPriceId());
        assertEquals(brandId, price.getBrandId());
        assertEquals(productId, price.getProductId());
        assertEquals(new BigDecimal("30.50"), price.getPrice());
        assertEquals(LocalDateTime.parse("2020-06-15T00:00:00"), price.getStartDate());
        assertEquals(LocalDateTime.parse("2020-06-15T11:00:00"), price.getEndDate());
    }

    @Test
    public void test5_Day16At21PM_ShouldReturnPrice4() {
        String endpoint = String.format(uri, port, brandId, productId, "2020-06-16T21:00:00");
        PriceDto price = restTemplate.getForObject(endpoint, PriceDto.class);

        assertEquals(Long.valueOf(4), price.getPriceId());
        assertEquals(brandId, price.getBrandId());
        assertEquals(productId, price.getProductId());
        assertEquals(new BigDecimal("38.95"), price.getPrice());
        assertEquals(LocalDateTime.parse("2020-06-15T16:00:00"), price.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), price.getEndDate());
    }

    @Test
    public void test6_Day13At10AM_ShouldReturn404NotFound() {
        String endpoint = String.format(uri, port, brandId, productId, "2020-06-13T10:00:00");
        ResponseEntity<PriceDto> responseEntity = restTemplate.getForEntity(endpoint, PriceDto.class);

        assertEquals(404, responseEntity.getStatusCode().value());
    }
}
