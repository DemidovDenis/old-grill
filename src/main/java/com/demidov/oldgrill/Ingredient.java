package com.demidov.oldgrill;

import lombok.Data;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        ONION, POTATO, VEGGIES, SAUCE
    }
}
