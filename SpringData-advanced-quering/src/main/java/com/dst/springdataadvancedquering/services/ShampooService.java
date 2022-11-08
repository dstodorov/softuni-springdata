package com.dst.springdataadvancedquering.services;

import com.dst.springdataadvancedquering.entities.Shampoo;
import com.dst.springdataadvancedquering.entities.Size;

import java.util.List;

public interface ShampooService {
    List<Shampoo> findByBrand(String brand);
    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findByIngredient(String ingredient);

    List<Shampoo> findByIngredients(List<String> ingredients);
}
