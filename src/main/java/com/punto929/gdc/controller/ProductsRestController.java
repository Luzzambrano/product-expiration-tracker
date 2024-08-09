package com.punto929.gdc.controller;

import com.punto929.gdc.entity.Product;
import com.punto929.gdc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductsRestController {
  private final ProductRepository repository;

  public record ProductDTO(
      String name,
      String sku,
      String brand,
      String description,
      Double cost,
      Double price,
      String category,
      String weight,
      Integer quantity,
      String stock,
      String supplier,
      String expirationDate
  ){}

  @GetMapping()
  public List<Product> getAllProducts(){
    return repository.findAll();
  }

  @PostMapping()
  public ResponseEntity<Product> addProduct(@RequestBody ProductDTO dto){
    Product p = Product.builder()
        .name(dto.name)
        .sku(dto.sku)
        .brand(dto.brand)
        .description(dto.description)
        .cost(BigDecimal.valueOf(dto.cost))
        .price(BigDecimal.valueOf(dto.price))
        .category(dto.category)
        .weight(dto.weight)
        .quantity(BigInteger.valueOf(dto.quantity))
        .stock(dto.stock)
        .supplier(dto.supplier)
        .expirationDate(LocalDate.parse(dto.expirationDate))
        .build();
    return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(p));
  }

  @GetMapping("/expiring")
  public List<Product> getProductsExpiringThisMonth() {
    LocalDate now = LocalDate.now();
    return repository.findProductsByExpiryDate(now.getMonthValue(), now.getYear());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto){
    Product p = repository.findById(id).orElse(null);
    Assert.notNull(p, "Product with id %d not found".formatted(id));
    p.setBrand(dto.brand);
    p.setCategory(dto.category);
    p.setCost(BigDecimal.valueOf(dto.cost));
    p.setDescription(dto.description);
    p.setName(dto.name);
    p.setPrice(BigDecimal.valueOf(dto.price));
    p.setQuantity(BigInteger.valueOf(dto.quantity));
    p.setStock(dto.stock);
    p.setSupplier(dto.supplier);
    p.setSku(dto.sku);
    p.setWeight(dto.weight);
    p.setExpirationDate(LocalDate.parse(dto.expirationDate));
    return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(p));
  }

  @DeleteMapping()
  public ResponseEntity<Void> deleteUser(Long id){
    Assert.notNull(repository.findById(id), "Product not found");
    repository.deleteById(id);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
  }
}
