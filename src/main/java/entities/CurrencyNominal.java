package entities;

/**
 * Class with initial amount of banknotes and their nominals
 */
public enum CurrencyNominal {
    ONE(1), FIVE(5), TEN(10), TWENTY(20), FIFTY(50);
    private int nominal;


    CurrencyNominal(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }

}
