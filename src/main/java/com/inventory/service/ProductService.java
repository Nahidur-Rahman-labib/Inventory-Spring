package com.inventory.service;

import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product save(Product product) {
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id);
    }

    public Product update(Product product) {
        return repository.update(product);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public List<Product> getExpiredProducts() {
        return repository.getExpiredProducts();
    }

    public List<Product> getLowStockProducts() {
        return repository.getLowStockProducts();
    }

    public List<Product> getExpiringProducts() {

        LocalDate nextWeek = LocalDate.now().plusDays(7);

        return repository.getExpiringProducts(nextWeek);
    }
}