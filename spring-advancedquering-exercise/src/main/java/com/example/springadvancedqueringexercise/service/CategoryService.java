package com.example.springadvancedqueringexercise.service;

import com.example.springadvancedqueringexercise.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
