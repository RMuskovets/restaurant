import org.junit.Test;
import org.roman.restaurant.Calculator;
import org.roman.restaurant.Food;
import org.roman.restaurant.Table;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class TableTest {

    public static final Table[] tables = {new Table(1), new Table(2), new Table(3)};

    @Test public void testTableClass() {
        assertNotNull(tables);
        for (Table t : tables) assertNotNull(t);
    }

    private double generateQuantity() {
        double d = new Random().nextDouble() * 180;
        if (d - (int) d > 0.5) d = (int) d + 0.5;
        else d = (int) d;
        return d;
    }

    private Food.FoodType generateType() {
        boolean a = new Random().nextBoolean();
        if (a) return Food.FoodType.FISH_SUSHI;
        else {
            a = new Random().nextBoolean();
            if (a) return Food.FoodType.AVOCADO_SUSHI;
            else {
                a = new Random().nextBoolean();
                if (a) return Food.FoodType.JUICE;
                else {
                    a = new Random().nextBoolean();
                    if (a) return Food.FoodType.COLA;
                    else return Food.FoodType.PEPSI;
                }
            }
        }
    }

    @Test public void testTableMethods() {
        for (Table t : tables) {
            t.use();
            Food.FoodType fa = generateType();
            Food.FoodType fb = generateType();
            t.addNewFood(new Food(fa));
            t.addFood(fa, generateQuantity());
            t.addNewFood(new Food(fb));
            t.addFood(fb, generateQuantity());
            System.out.println(Calculator.calculatePrice(t));
            Arrays.stream(t.getAllFoods()).forEach(System.out::println);
            System.out.println("--------|/|/|/|/--------- Was table " + t.getIndex());
            t.unuse();
        }
    }
}
