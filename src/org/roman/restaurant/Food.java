package org.roman.restaurant;

import java.util.EnumSet;

/**
 * Created by LINKOR on 27.06.2018 in 17:18.
 * Date: 2018.06.27
 */
public class Food {
    private String name = "Unknown food";

    public enum FoodCategory {
        DESSERT(1), PIZZA(2), DRINK(3), SOUP(4), SUSHI(5);
        private final int category;

        FoodCategory(int category) {
            this.category = category;
        }
    }

    public enum FoodType {
        /* SUSHI */ AVOCADO_SUSHI(FoodCategory.SUSHI, "Avocado Sushi"), FISH_SUSHI(FoodCategory.SUSHI, "Fish Sushi"),
        /* DRINK */ PEPSI(FoodCategory.DRINK, "Pepsi"), COLA(FoodCategory.DRINK, "Coca-Cola"), JUICE(FoodCategory.DRINK, "Fruit Juice")

        ;private final FoodCategory category;
        private final String name;

        FoodType(FoodCategory category, String name) {
            this.category = category;
            this.name = name;
        }

        public static FoodType getType(String name) {
            for (FoodType type : EnumSet.allOf(FoodType.class)) {
                if (type.name.equals(name)) return type;
            }
            return null;
        }

        public int getCategoryNumber() {
            return category.category;
        }
    }
}
