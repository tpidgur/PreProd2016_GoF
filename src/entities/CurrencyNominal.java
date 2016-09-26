package entities;

/**
 * Class with initial amount of banknotes and their nominals
 *
 * @author Pidhurska Tetiana
 * @version 1 (created on 25.09.16)
 */
public enum CurrencyNominal {
    ONE(1),FIVE(5),TEN(10), TWENTY(20),FIFTY(50);
    private int nominal;


    CurrencyNominal(int nominal) {
        this.nominal =nominal;
    }

    public int getNominal() {
        return nominal;
    }

}
