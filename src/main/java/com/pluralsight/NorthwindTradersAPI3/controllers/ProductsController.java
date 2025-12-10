package com.pluralsight.NorthwindTradersAPI3.controllers;

import com.pluralsight.NorthwindTradersAPI2.models.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {
    private List<Product> products;

    public ProductsController() {
        products = new ArrayList<>();

        products.add(new Product(1, "Laptop", 1, 899.99));
        products.add(new Product(2, "Wireless Mouse", 1, 29.99));
        products.add(new Product(3, "Orange Juice", 2, 4.50));
        products.add(new Product(4, "Coffee Beans", 2, 12.99));
        products.add(new Product(5, "Cheddar Cheese", 3, 6.75));
        products.add(new Product(6, "Whole Milk", 3, 3.99));
        products.add(new Product(7, "Sourdough Bread", 4, 5.25));
        products.add(new Product(8, "Croissants", 4, 8.50));
        products.add(new Product(9, "Tennis Racket", 5, 125.00));
        products.add(new Product(10, "Basketball", 5, 24.99));
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return products;
    }

    @RequestMapping(path = "/products/{productId}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }
}
