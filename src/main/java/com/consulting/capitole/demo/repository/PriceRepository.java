package com.consulting.capitole.demo.repository;

import com.consulting.capitole.demo.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p1 FROM Price p1 WHERE p1.brand.id = :brandId AND p1.product.id = :productId " +
            "AND p1.startDate <= :purchaseDate AND p1.endDate >= :purchaseDate " +
            "AND NOT EXISTS (" +
            "   SELECT 1 FROM Price p2 WHERE p2.brand.id = p1.brand.id AND p2.product.id = p2.product.id " +
            "   AND p2.startDate <= :purchaseDate AND p2.endDate >= :purchaseDate " +
            "   AND p2.priority > p1.priority" +
            ")")
    Price findPriceByBrandAndProductAndPurchaseDate(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("purchaseDate") LocalDateTime purchaseDate
    );
}
