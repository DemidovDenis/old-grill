package com.demidov.oldgrill;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Barbecue {

    private Long id;
    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message = "Длина имени должна составлять не менее 5 символов")
    private String name;

    @NotNull
    @Size(min=1, message = "Вы должны выбрать хотя бы 1 ингредиент")
    private List<Ingredient> ingredients;
}
