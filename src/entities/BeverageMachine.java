package entities;

import java.util.*;

/**
 * Class with initial amount of drinks and banknotes
 */
public class BeverageMachine {
    /**
     * map with key=DrinkForSale, value=available amount of drinks
     */
    private static Map<DrinkForSale, Integer> drinksAmount = new LinkedHashMap<>();
    /**
     * map with key=CurrencyNominal, value=available amount of banknotes of corresponding nominal
     */
    private static Map<CurrencyNominal, Integer> currencyAmount = new TreeMap<>(Collections.reverseOrder());

    /**
     * fill drinksAmount collection with initial amount of drinks of corresponding type
     */
    static {
        List<DrinkForSale> beverages = Arrays.asList(new DrinkForSale[]{
                new DrinkForSale(Beverage.LATE),
                new DrinkForSale(Beverage.ESPRESSO),
                new DrinkForSale(Beverage.CAPPUCCINO),
                new DrinkForSale(Beverage.MACCHIATO),
                new DrinkForSale(Beverage.ORANGE),
                new DrinkForSale(Beverage.PINEAPPLE),
                new DrinkForSale(Beverage.PEACH),
                new DrinkForSale(Beverage.MULTIFRUIT),
                new DrinkForSale(Beverage.BLACK_TEA),
                new DrinkForSale(Beverage.GREEN_TEA),
                new DrinkForSale(Beverage.PLASTIC_CUP)});
        Iterator<DrinkForSale> iter2 = beverages.iterator();
        while (iter2.hasNext()) {
            DrinkForSale drink = iter2.next();
            if (drink.getType() == Beverage.PLASTIC_CUP) {
                drinksAmount.put(drink, 300);
            } else drinksAmount.put(drink, 10);
        }
    }

    /**
     * fill currencyAmount collection with initial amount of drinks of corresponding type
     */
    static {
        currencyAmount.put(CurrencyNominal.FIVE, 10);
        currencyAmount.put(CurrencyNominal.TEN, 10);
        currencyAmount.put(CurrencyNominal.TWENTY, 10);
        currencyAmount.put(CurrencyNominal.FIFTY, 10);
        currencyAmount.put(CurrencyNominal.ONE, 10);
    }


    public static Map<DrinkForSale, Integer> getDrinksAmount() {
        return drinksAmount;
    }

    public static Map<CurrencyNominal, Integer> getCurrencyAmount() {
        return currencyAmount;
    }
}
