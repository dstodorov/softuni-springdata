package com.example.springdataexercise.services;

import com.example.springdataexercise.models.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
