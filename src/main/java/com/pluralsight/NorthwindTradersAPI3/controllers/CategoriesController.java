package com.pluralsight.NorthwindTradersAPI3.controllers;

import com.pluralsight.NorthwindTradersAPI3.dao.interfaces.ICategoryDAO;
import com.pluralsight.NorthwindTradersAPI3.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController {
    private ICategoryDAO categoryDAO;

    @Autowired
    public CategoriesController(ICategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    public List<Category> getAllCategories(@RequestParam(required = false) String name) {
        List<Category> categories = categoryDAO.getAll();

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
        return categoryDAO.getById(categoryId);
    }
}