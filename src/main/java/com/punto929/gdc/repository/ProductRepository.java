package com.punto929.gdc.repository;

import com.punto929.gdc.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("SELECT p FROM Product p WHERE MONTH(p.expirationDate) = :month AND YEAR(p.expirationDate) = :year")
  List<Product> findProductsByExpiryDate(@Param("month") int month, @Param("year") int year);
}
