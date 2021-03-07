package Pojo;

public class PurchaseUnits {
    private Amount amount;

    public PurchaseUnits(String currencyCode,String value) {
        this.amount = new Amount(currencyCode,value);
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
