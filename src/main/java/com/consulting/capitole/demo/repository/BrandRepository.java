package com.consulting.capitole.demo.repository;

import com.consulting.capitole.demo.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
