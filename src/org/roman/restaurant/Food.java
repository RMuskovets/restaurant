package org.roman.restaurant;

import java.util.EnumSet;

/**
 * Created by LINKOR on 27.06.2018 in 17:18.
 * Date: 2018.06.27
 */
public class Food {
    private String name;
    private final FoodType type;
    private double quantity = 1;

    public Food(FoodType type) {
        this.type = type;
        name = type.name;
    }

    public double quantity() {
        return quantity;
    }

    public void add(double plus) {
        quantity += plus;
    }

    public void remove(double minus) {
        quantity -= minus;
    }

    public int categoryNumber() {
        return type.getCategoryNumber();
    }

    public enum FoodCategory {
        DESSERT(1), PIZZA(2), DRINK(3), SOUP(4), SUSHI(5);
        private final int category;

        FoodCategory(int category) {
            this.category = category;
        }
    }

    public enum FoodType {
        /* SUSHI */ AVOCADO_SUSHI(FoodCategory.SUSHI, "Avocado Sushi", 35.50), FISH_SUSHI(FoodCategory.SUSHI, "Fish Sushi", 35.50),
        /* DRINK */ PEPSI(FoodCategory.DRINK, "Pepsi", 13.50), COLA(FoodCategory.DRINK, "Coca-Cola", 12.99)
        , JUICE(FoodCategory.DRINK, "Fruit Juice", 8.00),


        ;private final FoodCategory category;
        private final String name;
        private final double price;

        FoodType(FoodCategory category, String name, double price) {
            this.category = category;
            this.name = name;
            this.price = price;
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

        public double price() {
            return this.price;
        }
    }

    public String getName() {
        return name;
    }

    public FoodType getType() {
        return type;
    }
    public double myPrice() {
        return quantity * type.price;
    }

    public String toString() {
        return name + ", price: " + myPrice() + ", quantity: " + quantity;
    }
}
