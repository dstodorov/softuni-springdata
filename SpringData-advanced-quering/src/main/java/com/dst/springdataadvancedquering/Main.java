package com.dst.springdataadvancedquering;

import com.dst.springdataadvancedquering.entities.Ingredient;
import com.dst.springdataadvancedquering.entities.Size;
import com.dst.springdataadvancedquering.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    private ShampooService shampooService;

    @Autowired
    public Main(ShampooService shampooService) {
        this.shampooService = shampooService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.shampooService.findByBrand("Cotton Fresh").forEach(shampoo -> System.out.println(shampoo.getLabel().getTitle()));
        //this.shampooService.findByBrandAndSize("Cotton Fresh", Size.LARGE).forEach(shampoo -> System.out.println(shampoo.getId()));
        //p01_SelectShampooBySize();
        p03_FindShampooByIngredients();

    }

    void p01_SelectShampooBySize() {
        String inputSize = new Scanner(System.in).nextLine();

        this.shampooService.findAllBySizeOrderById(Size.valueOf(inputSize.toUpperCase()))
                .forEach(shampoo -> System.out.printf("%s %s %.2flv.%n", shampoo.getBrand(),
                        shampoo.getSize().name(),
                        shampoo.getPrice()));
    }

    void p02_FindShampooByIngredientName() {
        String ingredient = new Scanner(System.in).nextLine();

        this.shampooService.findByIngredient(ingredient)
                .forEach(shampoo -> System.out.printf("%s %s %.2flv.%n", shampoo.getBrand(),
                        shampoo.getSize().name(),
                        shampoo.getPrice()));
    }

    void p03_FindShampooByIngredients() {
        Scanner scanner = new Scanner(System.in);

        String ingredient = scanner.nextLine();
        List<String> ingredients = new ArrayList<>();
        do {
            ingredients.add(ingredient);

            ingredient = scanner.nextLine();
        } while (!ingredient.isEmpty());

        this.shampooService.findByIngredients(ingredients)
                .forEach(shampoo -> System.out.printf("%s %s %.2flv.%n", shampoo.getBrand(),
                        shampoo.getSize().name(),
                        shampoo.getPrice()));
    }
}
