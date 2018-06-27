package org.roman.restaurant;

public class Calculator {

    public static double calculatePrice(Table t) {
        Food[] foods = t.getAllFoods();
        double price = 0.0;
        for (Food f : foods) price += f.myPrice();
        return price;
    }
}
