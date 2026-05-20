package com.inventory.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Product name
    @Column(nullable = false, length = 100)
    private String name;

    // Current stock quantity
    @Column(nullable = false)
    private Integer quantity;

    // Minimum stock alert threshold
    @Column(name = "minimum_stock", nullable = false)
    private Integer minimumStock;

    // Product expiry date
    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    // Creation timestamp
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    // Automatically runs before insert
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Default constructor REQUIRED by JPA
    public Product() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Integer minimumStock) {
        this.minimumStock = minimumStock;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}