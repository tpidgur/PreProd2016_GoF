package entities;

/**
 * Class with names of beberages and corresponding prices
 */
public enum Beverage {
    LATE(10),
    ESPRESSO(15),
    CAPPUCCINO(20),
    MACCHIATO(25),
    ORANGE(30),
    PINEAPPLE(35),
    PEACH(40),
    MULTIFRUIT(45),
    GREEN_TEA(10),
    BLACK_TEA(10),
    PLASTIC_CUP(0);

    private int price;

    Beverage(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
