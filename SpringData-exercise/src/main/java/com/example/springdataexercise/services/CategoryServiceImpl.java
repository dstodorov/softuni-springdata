package com.example.springdataexercise.services;

import com.example.springdataexercise.models.Category;
import com.example.springdataexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        long size = categoryRepository.count();

        Random random = new Random();

        long categoriesCount = random.nextInt((int) size) + 1;

        Set<Integer> categoriesIds = new HashSet<>();

        for (long i = 0; i < categoriesCount; i++) {
            int nextId = random.nextInt((int)size) + 1;
            categoriesIds.add(nextId);
        }

        List<Category> categoriesById = this.categoryRepository.findAllById(categoriesIds);
        return new HashSet<>(categoriesById);
    }
}
