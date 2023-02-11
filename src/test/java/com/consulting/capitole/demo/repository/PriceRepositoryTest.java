package com.consulting.capitole.demo.repository;

import com.consulting.capitole.demo.model.Brand;
import com.consulting.capitole.demo.model.Price;
import com.consulting.capitole.demo.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ProductRepository productRepository;

    private final Long brandId = 1L;
    private final Long productId = 35455L;
    private final Brand brand = new Brand(brandId, "Zara");
    private final Product product = new Product(productId, brand, "Vestido estampado floral");
    private final Price price1 = new Price(1L, brand,
            LocalDateTime.parse("2020-06-14T00:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"),
            product, 0, BigDecimal.valueOf(35.50), "EUR");
    private final Price price2 = new Price(2L, brand,
            LocalDateTime.parse("2020-06-14T15:00:00"),
            LocalDateTime.parse("2020-06-14T18:30:00"),
            product, 1, BigDecimal.valueOf(25.45), "EUR");
    private final Price price3 = new Price(3L, brand,
            LocalDateTime.parse("2020-06-15T00:00:00"),
            LocalDateTime.parse("2020-06-15T11:00:00"),
            product, 1, BigDecimal.valueOf(30.50), "EUR");
    private final Price price4 = new Price(4L, brand,
            LocalDateTime.parse("2020-06-15T16:00:00"),
            LocalDateTime.parse("2020-12-31T23:59:59"),
            product, 1, BigDecimal.valueOf(38.95), "EUR");

    @Before
    public void init() {
        brandRepository.save(brand);
        productRepository.save(product);

        priceRepository.save(price1);
        priceRepository.save(price2);
        priceRepository.save(price3);
        priceRepository.save(price4);
    }

    @Test
    public void test1_Day14At10AM_ShouldReturnPrice1() {
        Price price = priceRepository.findPriceByBrandAndProductAndPurchaseDate(
                1L, 35455L, LocalDateTime.parse("2020-06-14T10:00:00"));

        assertEquals(brandId, price.getBrand().getId());
        assertEquals(productId, price.getProduct().getId());
        assertEquals(price1.getPriceId(), price.getPriceId());
        assertEquals(price1.getPrice(), price.getPrice());
        assertEquals(price1.getStartDate(), price.getStartDate());
        assertEquals(price1.getEndDate(), price.getEndDate());
        assertEquals(price1.getPriority(), price.getPriority());
        assertEquals(price1.getCurrency(), price.getCurrency());
    }

    @Test
    public void test2_Day14At16PM_ShouldReturnPrice2() {
        Price price = priceRepository.findPriceByBrandAndProductAndPurchaseDate(
                1L, 35455L, LocalDateTime.parse("2020-06-14T16:00:00"));

        assertEquals(brandId, price.getBrand().getId());
        assertEquals(productId, price.getProduct().getId());
        assertEquals(price2.getPriceId(), price.getPriceId());
        assertEquals(price2.getPrice(), price.getPrice());
        assertEquals(price2.getStartDate(), price.getStartDate());
        assertEquals(price2.getEndDate(), price.getEndDate());
        assertEquals(price2.getPriority(), price.getPriority());
        assertEquals(price2.getCurrency(), price.getCurrency());
    }

    @Test
    public void test3_Day14At21PM_ShouldReturnPrice1() {
        Price price = priceRepository.findPriceByBrandAndProductAndPurchaseDate(
                1L, 35455L, LocalDateTime.parse("2020-06-14T21:00:00"));

        assertEquals(brandId, price.getBrand().getId());
        assertEquals(productId, price.getProduct().getId());
        assertEquals(price1.getPriceId(), price.getPriceId());
        assertEquals(price1.getPrice(), price.getPrice());
        assertEquals(price1.getStartDate(), price.getStartDate());
        assertEquals(price1.getEndDate(), price.getEndDate());
        assertEquals(price1.getPriority(), price.getPriority());
        assertEquals(price1.getCurrency(), price.getCurrency());
    }

    @Test
    public void test4_Day15At10AM_ShouldReturnPrice3() {
        Price price = priceRepository.findPriceByBrandAndProductAndPurchaseDate(
                1L, 35455L, LocalDateTime.parse("2020-06-15T10:00:00"));

        assertEquals(brandId, price.getBrand().getId());
        assertEquals(productId, price.getProduct().getId());
        assertEquals(price3.getPriceId(), price.getPriceId());
        assertEquals(price3.getPrice(), price.getPrice());
        assertEquals(price3.getStartDate(), price.getStartDate());
        assertEquals(price3.getEndDate(), price.getEndDate());
        assertEquals(price3.getPriority(), price.getPriority());
        assertEquals(price3.getCurrency(), price.getCurrency());
    }

    @Test
    public void test5_Day16At21PM_ShouldReturnPrice4() {
        Price price = priceRepository.findPriceByBrandAndProductAndPurchaseDate(
                1L, 35455L, LocalDateTime.parse("2020-06-16T21:00:00"));

        assertEquals(brandId, price.getBrand().getId());
        assertEquals(productId, price.getProduct().getId());
        assertEquals(price4.getPriceId(), price.getPriceId());
        assertEquals(price4.getPrice(), price.getPrice());
        assertEquals(price4.getStartDate(), price.getStartDate());
        assertEquals(price4.getEndDate(), price.getEndDate());
        assertEquals(price4.getPriority(), price.getPriority());
        assertEquals(price4.getCurrency(), price.getCurrency());
    }

    @Test
    public void test6_Day13At10AM_ShouldReturnNull() {
        Price price = priceRepository.findPriceByBrandAndProductAndPurchaseDate(
                1L, 35455L, LocalDateTime.parse("2020-06-13T10:00:00"));

        assertNull(price);
    }
}
