// Authors: Group D
//   Sobczak Dominik
//   Śmigowski Piotr
//   Twardosz Sławomir
package currencychanger;

public class Exchange {//przelicznik

    public static double changeCurrency(Currency cur1, Currency cur2, double amount) {
        return (cur1.getExchangeRate() / cur1.getQuantity() * amount)
                / (cur2.getExchangeRate() / cur2.getQuantity());
    }

}
