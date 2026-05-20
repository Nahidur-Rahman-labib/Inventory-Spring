package com.inventory.repository;

import com.inventory.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    // SAVE
    public Product save(Product product) {

        em.persist(product);

        return product;
    }

    // GET ALL
    public List<Product> findAll() {

        return em.createQuery(
                "SELECT p FROM Product p",
                Product.class
        ).getResultList();
    }

    // FIND BY ID
    public Product findById(Long id) {

        return em.find(Product.class, id);
    }

    // UPDATE
    public Product update(Product product) {

        return em.merge(product);
    }

    // DELETE
    public void delete(Long id) {

        Product product = findById(id);

        if (product != null) {
            em.remove(product);
        }
    }

    // EXPIRED PRODUCTS
    public List<Product> getExpiredProducts() {

        return em.createQuery(
                "SELECT p FROM Product p " +
                        "WHERE p.expiryDate < CURRENT_DATE",
                Product.class
        ).getResultList();
    }

    // LOW STOCK PRODUCTS
    public List<Product> getLowStockProducts() {

        return em.createQuery(
                "SELECT p FROM Product p " +
                        "WHERE p.quantity < p.minimumStock",
                Product.class
        ).getResultList();
    }

    // EXPIRING SOON
    public List<Product> getExpiringProducts(LocalDate nextWeek) {

        return em.createQuery(
                        "SELECT p FROM Product p " +
                                "WHERE p.expiryDate <= :nextWeek " +
                                "AND p.expiryDate >= CURRENT_DATE",
                        Product.class
                )
                .setParameter("nextWeek", nextWeek)
                .getResultList();
    }
}