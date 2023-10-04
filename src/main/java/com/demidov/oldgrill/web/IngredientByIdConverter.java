package com.demidov.oldgrill.web;

import com.demidov.oldgrill.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.demidov.oldgrill.Ingredient;
import com.demidov.oldgrill.Ingredient.Type;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
//    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

//    public IngredientByIdConverter() {
//        ingredientMap.put("PION",
//                new Ingredient("PION", "Маринованный лук", Type.ONION));
//        ingredientMap.put("BAPO",
//                new Ingredient("BAPO", "Запеченный картофель", Type.POTATO));
//        ingredientMap.put("TOCO",
//                new Ingredient("TOCO", "Помидоры на углях", Type.VEGGIES));
//        ingredientMap.put("ZUCO",
//                new Ingredient("ZUCO", "Кабачки на углях", Type.VEGGIES));
//        ingredientMap.put("LETC",
//                new Ingredient("LETC", "Салат", Type.VEGGIES));
//        ingredientMap.put("KESA",
//                new Ingredient("KESA", "Шашлычный соус", Type.SAUCE));
//        ingredientMap.put("SOSA",
//                new Ingredient("SOSA", "Сметанный соус", Type.SAUCE));
//    }
    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
