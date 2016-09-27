package mvc;

import entities.Beverage;
import entities.BeverageMachine;
import entities.CurrencyNominal;
import entities.DrinkForSale;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The program logic is demonstrated.
 * Provides methods, that allow to get drink from the Map(from BeverageMachine) on its id,
 * order drink (by decreasing the amount of the left drinks),
 * calculate total  sum from a list of banknotes, given by customer etc.
 */
public class Model {

    /**
     * order drink by decreasing its amount by 1
     *
     * @param drinkId -int id of the drink
     */
    public void orderDrink(int drinkId) {
        Map.Entry<DrinkForSale, Integer> entry = getDrinkMapOnId(drinkId);
        if (entry.getValue() > 0) {//checks if the available amount of drinks>0
            entry.setValue(entry.getValue() - 1);
        }
    }

    /**
     * searches drink on its id from the map
     *
     * @param id -int
     * @return Map.Entry<DrinkForSale, Integer>  with key DrinkForSale and value = remained balance of the corresponding drink
     * or null if there  no drink with such id
     */
    public Map.Entry<DrinkForSale, Integer> getDrinkMapOnId(int id) {
        Iterator<Map.Entry<DrinkForSale, Integer>> iter = BeverageMachine.getDrinksAmount().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<DrinkForSale, Integer> entry = iter.next();
            if (entry.getKey().getId() == id) {//get required drink on its id
                return entry;
            }
        }
        return null;
    }

    /**
     * calculates sum of int values from the List
     *
     * @param banknotes List of the integer banknotes put in the coffee machine
     * @return int sum of the list of banknotes
     */
    public int calculateSumOfPutMoney(List<Integer> banknotes) {
        return banknotes.stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * prints for the user the available drinks
     */
    public void printAvailableBeverages() {
        Iterator<Map.Entry<DrinkForSale, Integer>> iter = BeverageMachine.getDrinksAmount().entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<DrinkForSale, Integer> entry = iter.next();
            if (entry.getKey().getType().equals(Beverage.PLASTIC_CUP)) {
                continue;
            }
            if (entry.getValue() <= 0) {//if the required beverage is not available it won't be printed
                continue;
            }
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * return the Enum name representation of the drink on its id
     *
     * @param beverageId - int id of the drink
     * @return - String name of the drink
     */
    public String getDrinkName(int beverageId) {
        return getDrinkMapOnId(beverageId).getKey().getType().name();
    }

    /**
     * @return currencyAmount Map from the Coffeemachine class
     */
    public Map<CurrencyNominal, Integer> getCurrencyMap() {
        return BeverageMachine.getCurrencyAmount();
    }

    /**
     * gives the change to the customer, starting to give the biggest nominals of the banknotes closest to the change,then less and so on
     * in case if there is no such banknote, the change wont be fully gave back
     *
     * @param given- int total sum of money, put by customer
     * @param price  - int price of the drink
     */
    public List<Integer> getChange(int given, int price) {
        Map<CurrencyNominal, Integer> availCurrency = getCurrencyMap();
        List<Integer> change = new LinkedList<>();
        int requiredChange = given - price;
        Iterator<Map.Entry<CurrencyNominal, Integer>> iter = availCurrency.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<CurrencyNominal, Integer> entry = iter.next();
            int banknote = entry.getKey().getNominal();
            int amount = entry.getValue();
            while (banknote <= requiredChange && amount > 0) {
                change.add(banknote);
                requiredChange = requiredChange - banknote;
                entry.setValue(amount--);
                new View().printMessage(View.CHANGE + banknote);
            }
        }
        return change;
    }
}
