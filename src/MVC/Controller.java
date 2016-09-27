package mvc;

import entities.CurrencyNominal;
import entities.DrinkForSale;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Sends commands to the model to update the model's state.
 * It can also send commands to its associated view to change the view's presentation of the model
 */
public class Controller {
    Model model;
    View view;
    Scanner sc;
    /**
     * list of banknotes put by the customer in coffee machine
     */
    List<Integer> banknotes;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        sc = new Scanner(System.in);
        banknotes = new LinkedList<>();
    }

    /**
     * The Work method
     * processes ordering of the drink with passing money to the machine and receiving change from it
     */
    public void processUser() {
        view.printMessage(View.AVAILABLE_BEVERAGES);
        model.printAvailableBeverages();
        putMoneyInCoffeeMachine();
        int beverageId = inputDrinkIdWithScanner();
        giveBeverage(banknotes, beverageId);
    }

    /**
     * util method that parses int
     *
     * @param message - prints the String invitation message
     * @return int value
     */
    public int inputIntWithScanner(String message) {
        view.printMessage(message);
        while (!sc.hasNextInt()) {
            view.printMessage(view.WRONG_INPUT);
            sc.next();
        }
        return sc.nextInt();
    }

    /**
     * parses int number and checks whether such lies in the acceptable range of ids of DrinkForSale
     *
     * @return int id of the DrinkForSale
     */
    public int inputDrinkIdWithScanner() {
        view.printMessage(View.AVAILABLE_BEVERAGES);
        model.printAvailableBeverages();
        while (true) {
            int res = inputIntWithScanner(View.SELECT_BEVERAGE);
            //checks if such id of Drink exists in the range(1,counter)
            OptionalInt containsValue = IntStream.range(1, DrinkForSale.getCounter()).filter(p -> p == res).findAny();
            if (containsValue.isPresent()) {
                view.printMessage(view.CHOSEN_BEVERAGE_TYPE + model.getDrinkName(res));
                return res;
            } else {
                view.printMessage(view.WRONG_INPUT);
            }
        }
    }

    /**
     * allows:
     * to pass banknotes to the coffee machine or
     * to exit from putting money and move to choosing drinks or
     * to discard the overall order
     */
    public void putMoneyInCoffeeMachine() {
        while (true) {
            int res = inputIntWithScanner(view.PUT_MONEY);
            switch (res) {
                case -1://discard
                    System.exit(0);
                case 0://exit from putting money
                    view.printMessage(View.INPUT_CURRENCY + banknotes);
                    return;
                default://put money
                    Optional containsValue = EnumSet.allOf(CurrencyNominal.class).stream().filter(e -> e.getNominal() == res).findAny();
                    if (containsValue.isPresent()) {
                        banknotes.add(res);
                        view.printMessage(View.CURRENT_INPUT + model.calculateSumOfPutMoney(banknotes));
                    } else {
                        view.printMessage(view.WRONG_INPUT);
                    }
            }
        }
    }

    /**
     * allows to give beverage and charge in case if enough money are provided
     * or to move to the "passing money" stage otherwise
     *
     * @param banknotes  - List of integer banknotes, passed by customer to the coffee machine
     * @param beverageId - int id of the drink, chosen by the customer
     */
    public void giveBeverage(List<Integer> banknotes, int beverageId) {
        int total = model.calculateSumOfPutMoney(banknotes);
        int price = model.getDrinkMapOnId(beverageId).getKey().getPrice();
        if (total < price) {//customer put not enough money
            view.printMessage(view.NOT_ENOUGH_MONEY);
            putMoneyInCoffeeMachine();
        } else if (total == price) {
            view.printMessage(model.getDrinkMapOnId(beverageId) + view.SUCCESS);
            model.orderDrink(beverageId);
        } else if (total > price) {
            view.printMessage(View.REQUIRED_CHANGE + (total - price));
            view.printMessage(View.TOTAL_CHANGE + model.calculateSumOfPutMoney(model.getChange(total, price)));
        }
    }

}

