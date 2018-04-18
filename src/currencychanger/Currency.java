// Authors: Group D
//   Sobczak Dominik
//   Śmigowski Piotr
//   Twardosz Sławomir
package currencychanger;

public class Currency {//waluta

    private String currencyName, currencyCode;//nazwa_waluty,kod_waluty
    private int quantity;//przelicznik
    private double exchangeRate;//kurs_sredni

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
