package MVC;

import entities.CoffeeMachine;
import entities.CurrencyNominal;
import entities.DrinkForSale;
import org.junit.Test;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Tetiana_Pidhurska on 9/26/2016.
 */
public class ModelTest {
    Model model;
    Controller controller;

    {
        model = new Model();
        controller = new Controller(model, new View());
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
        int amount = CoffeeMachine.getDrinksAmount().get(drink);
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
    /**
     * Tests if change is given correctly:
     * starting from the largest banknote and up to the lowest, until the banknotes are available
     */
    @Test
    public void getChangeTest() {
        List<Integer>  actual= model.getChange(550, 35);
        List<Integer> expected= Arrays.asList(new Integer[]{50,50,50,50,50,50,50,50,50,50,10,5});
        assertEquals(expected,actual );
    }

}