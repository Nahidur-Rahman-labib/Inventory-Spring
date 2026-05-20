package com.inventory.controller;

import com.inventory.entity.Product;
import com.inventory.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // CREATE
    @PostMapping
    public Product save(@RequestBody Product product) {

        return service.save(product);
    }

    // GET ALL
    @GetMapping
    public List<Product> getAll() {

        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Product getById(
            @PathVariable("id") Long id
    ) {

        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Product update(

            @PathVariable("id") Long id,

            @RequestBody Product product
    ) {

        product.setId(id);

        return service.update(product);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable("id") Long id
    ) {

        service.delete(id);

        return "Deleted Successfully";
    }

    // EXPIRED PRODUCTS
    @GetMapping("/expired")
    public List<Product> expiredProducts() {

        return service.getExpiredProducts();
    }

    // LOW STOCK
    @GetMapping("/low-stock")
    public List<Product> lowStockProducts() {

        return service.getLowStockProducts();
    }

    // EXPIRING SOON
    @GetMapping("/expiring")
    public List<Product> expiringProducts() {

        return service.getExpiringProducts();
    }
}