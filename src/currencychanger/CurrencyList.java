//Authors: Group C
//Zybek Anna
//Wlaz³o Kinga
//Sznajder Izabela
//Szostak Szymon

package currencychanger;

import java.util.ArrayList;
import java.util.List;

public class CurrencyList {
	
	private List<Currency> currencyList; // lista walut
	public CurrencyList()
	{
		currencyList = new ArrayList<Currency>();
	}
	public CurrencyList(List<Currency> curList)
	{
		currencyList = new ArrayList<Currency>();
		currencyList = curList;
	}
	
	public Currency get(String codeName)
	{
		for (Currency currency : currencyList) 
	       if(currency.getCurrencyCode().equals(codeName))
	    	   return currency;
	        
	    return null;
	}
	
	public String[] getCodes()
	{
		List<String> codes=new ArrayList<String>();
		for(Currency currency : currencyList)
			codes.add(currency.getCurrencyCode());
		return codes.toArray(new String[0]);
	}
	
}