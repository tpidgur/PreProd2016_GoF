package mvc;

/**
 * A view generates an output presentation to the user based on changes in the model.
 */
public class View {
    public static final String SELECT_BEVERAGE = "\nSelect beverage from a list (enter the id number of the beverage):\n";

    public static final String AVAILABLE_BEVERAGES = "THE AVAILABLE DRINKS and their amounts are:\n";

    public static final String WRONG_INPUT = "Wrong input!\n";

    public static final String PUT_MONEY = "\nPlease put money with nominals of 1,5,10,20,50 in the coffee-machine or" +
            " 0 in order to choose the drink or -1 " +
            "if you want to discard an order. \n";
    public static final String INPUT_CURRENCY = "You've put the currency: ";
    public static final String CURRENT_INPUT = "Your current balance is: ";

    public static final String NOT_ENOUGH_MONEY = "Not enough money!\n";
    public static final String SUCCESS = " is being prepared...\n";
    public static final String CHOSEN_BEVERAGE_TYPE = "The chosen drink is:";
    public static final String REQUIRED_CHANGE = "\nRequired change is: \n";
    public static final String CHANGE = "Get banknote nominal: \n";
    public static final String TOTAL_CHANGE = "The total change makes up: \n";


    public void printMessage(String message) {
        System.out.println(message);
    }
}
