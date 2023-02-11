package com.consulting.capitole.demo.repository;

import com.consulting.capitole.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
