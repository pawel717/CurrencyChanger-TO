// Authors: Group A
//   Zajdel Mateusz 
//   Wójcik Adrian 
//   Twardzik Rafał 
package currencychanger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {

    static final String URL_XML_NBP = "http://www.nbp.pl/kursy/xml/lasta.xml";
    static final String URL_JSON_NBP = "http://api.nbp.pl/api/exchangerates/tables/A/";

    public static String get(DownloaderType downloaderType) throws Exception {

        String urlToRequest;

        if (downloaderType == DownloaderType.XML) {
            urlToRequest = URL_XML_NBP;
        } else {
            urlToRequest = URL_JSON_NBP;
        }

        URL website = new URL(urlToRequest);
        HttpURLConnection connection = (HttpURLConnection) website.openConnection();
        connection.setRequestMethod("GET");
        if (downloaderType == DownloaderType.JSON) {
            connection.setRequestProperty("Accept", "application/json");
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        return response.toString();
    }
}
