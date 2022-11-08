package com.dst.springdataadvancedquering.repositories;

import com.dst.springdataadvancedquering.entities.Shampoo;
import com.dst.springdataadvancedquering.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    List<Shampoo> findAllBySizeOrderById(Size size);

    @Query("SELECT s FROM Shampoo as s JOIN s.ingredients as i WHERE i.name = :name")
    List<Shampoo> findByIngredient(@Param("name") String ingredient);

    @Query("SELECT s FROM Shampoo as s JOIN s.ingredients as i WHERE i.name IN :ingredients")
    List<Shampoo> findByIngredients(List<String> ingredients);
}
