package com.demidov.oldgrill.web;

import com.demidov.oldgrill.Barbecue;
import com.demidov.oldgrill.BarbecueOrder;
import com.demidov.oldgrill.Ingredient;
import com.demidov.oldgrill.data.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.demidov.oldgrill.Ingredient.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("barbecueOrder")
public class DesignBarbecueController {

    private final IngredientRepository ingredientRepo;
    @Autowired
    public DesignBarbecueController(
            IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("PION", "Маринованный лук", Type.ONION),
                new Ingredient("BAPO", "Запеченный картофель", Type.POTATO),
                new Ingredient("TOCO", "Помидоры на углях", Type.VEGGIES),
                new Ingredient("ZUCO", "Кабачки на углях", Type.VEGGIES),
                new Ingredient("LETC", "Салат", Type.VEGGIES),
                new Ingredient("KESA", "Шашлычный соус", Type.SAUCE),
                new Ingredient("SOSA", "Сметанный соус", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "barbecueOrder")
    public BarbecueOrder order() {
        return new BarbecueOrder();
    }

    @ModelAttribute(name = "barbecue")
    public Barbecue barbecue() {
        return new Barbecue();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processBarbecue(@Valid Barbecue barbecue, Errors errors,
                              @ModelAttribute BarbecueOrder barbecueOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        barbecueOrder.addBarbecue(barbecue);
        log.info("Processing barbecue: {}", barbecue);
        return "redirect:/orders/current";
    }
}
