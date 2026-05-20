package com.inventory.scheduler;

import com.inventory.entity.Product;
import com.inventory.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpiryChecker {

    @Autowired
    private ProductRepository productRepository;

    // Runs every day at 1 AM
    @Scheduled(cron = "0 0 1 * * ?")
    public void checkExpiredProducts() {

        List<Product> expiredProducts =
                productRepository.getExpiredProducts();

        System.out.println(
                "Expired Products: "
                        + expiredProducts.size()
        );

        for (Product product : expiredProducts) {

            System.out.println(
                    "Expired: " + product.getName()
            );
        }
    }
}
