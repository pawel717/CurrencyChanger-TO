// Authors: Group B
//   Sykała Wojciech 
//   Zub Piotr
//   Sucharzewski Paweł
package currencychanger;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CurrencyXMLParser implements ICurrencyParser {

    public static CurrencyList getList(String data) {
        List<Currency> currencyList = new ArrayList<>();

        Currency pln = new Currency();
        pln.setCurrencyName("Złoty");
        pln.setQuantity(1);
        pln.setCurrencyCode("PLN");
        pln.setExchangeRate(1);
        currencyList.add(pln);

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new InputSource(new StringReader(data)));
            document.getDocumentElement().normalize();
            NodeList rawCurrencyList = document.getElementsByTagName("pozycja");

            for (int i = 0; i < rawCurrencyList.getLength(); i++) {
                Node rawCurrencyNode = rawCurrencyList.item(i);

                if (rawCurrencyNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element rawCurrencyElement = (Element) rawCurrencyNode;
                    Currency currency = new Currency();

                    String currencyName = rawCurrencyElement
                            .getElementsByTagName("nazwa_waluty")
                            .item(0)
                            .getTextContent();
                    currency.setCurrencyName(currencyName);

                    int quantity = Integer.parseInt(
                            rawCurrencyElement
                                    .getElementsByTagName("przelicznik")
                                    .item(0)
                                    .getTextContent());
                    currency.setQuantity(quantity);

                    String currencyCode = rawCurrencyElement
                            .getElementsByTagName("kod_waluty")
                            .item(0)
                            .getTextContent();
                    currency.setCurrencyCode(currencyCode);

                    double exchangeRate = Double.parseDouble(
                            rawCurrencyElement
                                    .getElementsByTagName("kurs_sredni")
                                    .item(0)
                                    .getTextContent()
                                    .replace(',', '.'));
                    currency.setExchangeRate(exchangeRate);

                    currencyList.add(currency);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(CurrencyXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new CurrencyList(currencyList);
    }
}
