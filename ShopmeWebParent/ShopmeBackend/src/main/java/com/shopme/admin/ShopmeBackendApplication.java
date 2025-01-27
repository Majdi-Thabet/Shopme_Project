package com.shopme.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.shopme.common.entity") // Scanne le package des entités
public class ShopmeBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopmeBackendApplication.class, args);
    }
}
