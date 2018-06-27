import org.junit.Test;
import org.roman.restaurant.Food;

public class FoodTest {

    @Test
    public void testFoodClass() {
        new Food(Food.FoodType.FISH_SUSHI); new Food(Food.FoodType.AVOCADO_SUSHI);
    }

    @Test
    public void testFoodMethods() {
        Food f = new Food(Food.FoodType.FISH_SUSHI);
        f.add(0.5);
        f.remove(0.7);
        System.out.println(f.quantity());
        System.out.println(f.categoryNumber());
    }
}
