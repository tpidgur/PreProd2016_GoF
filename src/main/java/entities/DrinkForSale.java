package entities;

/**
 * Class containing data about type of beverage, id, price
 */
public class DrinkForSale {
    private int id;
    private static int counter = 1;
    private Beverage type;
    private int price;

    public DrinkForSale(Beverage type) {
        this.type = type;
        this.price = type.getPrice();
        id = counter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Beverage getType() {
        return type;
    }

    public void setType(Beverage type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return type.toString() + "(id" + getId() + ",price=" + type.getPrice() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkForSale that = (DrinkForSale) o;
        if (getPrice() != that.getPrice()) return false;
        return getType() == that.getType();
    }

    @Override
    public int hashCode() {
        int result = getType().hashCode();
        result = 31 * result + getPrice();
        return result;
    }
}
