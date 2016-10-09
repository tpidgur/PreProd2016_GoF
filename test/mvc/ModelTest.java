package mvc;

import entities.BeverageMachine;
import entities.DrinkForSale;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ModelTest {
    Model model;

    @Before
    public void initializeModel() {
        model = new Model();
    }


    /**
     * Tests if  applying  the orderDrink() the available amount of drink  (let say with id 5)
     * decreases the total amount of remaining drinks by 1.
     * Assume, that there are 10 initially items of each type
     */
    @Test
    public void orderDrinkTest() {
        model.orderDrink(5);
        DrinkForSale drink = model.getDrinkMapOnId(5).getKey();
        int amount = BeverageMachine.getDrinksAmount().get(drink);
        assertEquals(9, amount);
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