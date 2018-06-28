package org.roman.restaurant;

import java.util.*;
import static org.roman.restaurant.Food.FoodType;

/**
 * Created by LINKOR on 27.06.2018 in 17:17.
 * Date: 2018.06.27
 */
public class Table {

    private final int index;
    private final Map<FoodType, Food> foods = new HashMap<>();
    private boolean used = false;

    public Table(int index) {
        this.index = index;
    }

    public void use() {
        if (!used) used = true;
    }

    public void unuse() {
        if (used) {
            used = false;
            foods.clear();
        }
    }

    public boolean isUsed(){
        return used;
    }

    public void addNewFood(Food food) {
        foods.put(food.getType(), food);
    }
    public void addFood(FoodType type, double quantity) {
        Food f = foods.get(type);
        f.add(quantity);
        foods.put(type, f);
    }

    public int getIndex() {
        return index;
    }

    public Food[] getAllFoods() {
        int size = foods.size();
        final int[] i = {0};
        Food[] foods = new Food[size];
        this.foods.forEach((k, v) -> foods[i[0]++] = v);
        return foods;
    }
}
