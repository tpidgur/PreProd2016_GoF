package mvc;

import entities.BeverageMachine;
import entities.DrinkForSale;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class ModelTest {
    public static final int COFFEE = 5;
    Model model;

    @Before
    public void initializeModel() {
        model = new Model();
    }


    @Test
    public void orderDrinkTest() {
        DrinkForSale drink = getDrink(COFFEE);
        int initialDrinksAmount = getRemainingDrinks(drink);
        model.orderDrink(COFFEE);
        assertThat(getRemainingDrinks(drink), is(initialDrinksAmount - 1));

    }

    private Integer getRemainingDrinks(DrinkForSale drink) {
        return BeverageMachine.getDrinksAmount().get(drink);
    }

    private DrinkForSale getDrink(int drinkId) {
        return model.getDrinkMapOnId(drinkId).getKey();
    }


    /**
     * Tests if on id of the drink, the corresponding String name of it can be obtained
     */
    @Test
    public void getDrinkNameTest() {
        String name = model.getDrinkName(5);
        assertEquals("ORANGE", name);
    }

    @Test
    public void getChangeTest() {
        List<Integer> actual = model.getChange(550, 35);
        List<Integer> expected = Arrays.asList(50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 10, 5);
        assertEquals(expected, actual);

    }

}