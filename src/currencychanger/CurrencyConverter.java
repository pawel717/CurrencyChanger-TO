// Authors: Group E
//   Pawe³ Suchanicz
//   Przemys³aw Strojny
//   Joanna Waligóra
//   £ukasz Sujkowski

package currencychanger;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CurrencyConverter extends JFrame{

    private final JPanel panel;
    private final Dimension screenSize;
    private final int width;
    private final int height;
    
    public CurrencyConverter()
    {
        screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        width=screenSize.width;
        height=screenSize.height;
        
        setSize(280, 350);
        setLocation(width/2-width/4, height/2-height/4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        panel=new View();
        add(panel);
        setVisible(true);
        
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame=new CurrencyConverter();  
                
            }
        });
        
    }
    
}