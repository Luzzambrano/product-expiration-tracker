package com.punto929.gdc.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @SequenceGenerator(sequenceName = "product_id_sequence", name = "product_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_sequence")
    Long id;
    @Column
    String name;
    @Column
    String sku;
    @Column
    String brand;
    @Column
    String description;
    @Column
    BigDecimal cost;
    @Column
    BigDecimal price;
    @Column
    String category;
    @Column
    String weight;
    @Column
    BigInteger quantity;
    @Column
    String stock;
    @Column
    String supplier;
    @Column
    @JsonProperty("expiration_date")
    LocalDate expirationDate;
}
