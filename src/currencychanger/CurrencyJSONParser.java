// Authors: Group B
//   Sykała Wojciech
//   Zub Piotr
//   Sucharzewski Paweł
package currencychanger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*; // remember to add org.json.jar file to your project

public class CurrencyJSONParser implements ICurrencyParser {

    public static CurrencyList getList(String data) {
        List<Currency> currencyList = new ArrayList<>();

        Currency pln = new Currency();
        pln.setCurrencyName("Złoty");
        pln.setQuantity(1);
        pln.setCurrencyCode("PLN");
        pln.setExchangeRate(1);
        currencyList.add(pln);

        try {
            // Problems with '[' as first and last char. Need to trim the string.
            data = data.substring(1, data.length() - 1);

            JSONObject document = new JSONObject(data);
            JSONArray rawCurrencyList = document.getJSONArray("rates");

            for (int i = 0; i < rawCurrencyList.length(); i++) {
                JSONObject rawCurrencyObject = rawCurrencyList.getJSONObject(i);

                Currency currency = new Currency();

                String currencyName = (String) rawCurrencyObject.get("currency");
                currency.setCurrencyName(currencyName);

                int quantity = 1;
                currency.setQuantity(quantity);

                String currencyCode = (String) rawCurrencyObject.get("code");
                currency.setCurrencyCode(currencyCode);

                double exchangeRate = (Double) rawCurrencyObject.get("mid");
                currency.setExchangeRate(exchangeRate);

                currencyList.add(currency);
            }
        } catch (JSONException ex) {
            Logger.getLogger(CurrencyJSONParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new CurrencyList(currencyList);
    }
}
