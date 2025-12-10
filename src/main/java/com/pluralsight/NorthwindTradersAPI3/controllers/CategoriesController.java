package com.pluralsight.NorthwindTradersAPI3.controllers;

import com.pluralsight.NorthwindTradersAPI3.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController {
    private List<Category> categories;

    public CategoriesController() {
        categories = new ArrayList<>();

        categories.add(new Category(1, "Electronics"));
        categories.add(new Category(2, "Beverages"));
        categories.add(new Category(3, "Dairy Products"));
        categories.add(new Category(4, "Bakery"));
        categories.add(new Category(5, "Sporting Goods"));
    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public List<Category> getAllCategories(@RequestParam(required = false) String name) {
        if (name == null) {
            return categories;
        }

        List<Category> filteredCategories = new ArrayList<>();
        for (Category category : categories) {
            if (category.getCategoryName().equalsIgnoreCase(name)) {
                filteredCategories.add(category);
            }
        }

        return filteredCategories;
    }

    @RequestMapping(path = "/categories/{categoryId}", method = RequestMethod.GET)
    public Category getCategoryById(@PathVariable int categoryId) {
        for (Category category : categories) {
            if (category.getCategoryId() == categoryId) {
                return category;
            }
        }

        return null;
    }
}