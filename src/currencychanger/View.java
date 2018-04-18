// Authors: Group E
//   Pawe� Suchanicz
//   Przemys�aw Strojny
//   Joanna Walig�ra
//   �ukasz Sujkowski

package currencychanger;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class View extends JPanel{
    
    private final JTextField inputValue, resultValue;
    private final JComboBox <String>codeList, newCodeList; //listy z kodami walut
    private CurrencyList currencyList;
    
    public View()
    {
        setLayout(null);
        
        String data = null;
		try {
			data = Downloader.get(DownloaderType.JSON);
		} catch (Exception ex) {
			Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		}
        currencyList=CurrencyJSONParser.getList(data);
        
        inputValue = new JTextField();
        inputValue.setBounds(30, 40, 220, 30);
        inputValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();
	            if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) 
	            {
	              e.consume();
	            }
        }});
        inputValue.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
              calculate();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
              calculate();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
              calculate();
        }});
       
        codeList = new JComboBox<String>(currencyList.getCodes());
        codeList.setBounds(30, 100, 220, 30);
        codeList.addActionListener(new ActionListener() {
        @Override
	        public void actionPerformed(ActionEvent ae) {
	            calculate();
        }});
        
        newCodeList = new JComboBox<String>(currencyList.getCodes());
        newCodeList.setBounds(30, 160, 220, 30);
        newCodeList.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            calculate();
        }});
        
        resultValue = new JTextField();
        resultValue.setEditable(false);
        resultValue.setBackground(Color.WHITE);
        resultValue.setBounds(30, 220, 220, 30);
        
        add(inputValue);
        add(codeList);
        add(newCodeList);
        add(resultValue);
        
    }
    
    //metoda obliczająca wartość waluty, wywoływana przy zmianie JTextField, ComboBox
    //+ zrobić obsługę złego formatu inputValue
    private void calculate()
    {
        
        Double value;
        if(!inputValue.getText().equals(""))
        {
            try
            {
                value = Double.parseDouble(inputValue.getText());
            }

            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Błędny format");
                return;
            }
            
            String inputCode=String.valueOf(codeList.getSelectedItem());
            String outputCode=String.valueOf(newCodeList.getSelectedItem());
            Double result=Exchange.changeCurrency(currencyList.get(inputCode), currencyList.get(outputCode), value);
      
            resultValue.setText(result.toString());
        
        }
        else
            resultValue.setText("");
    }
    
}