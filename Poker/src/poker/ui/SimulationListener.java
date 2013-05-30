

package poker.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import poker.PokerCalculator;
import poker.Simulator;

/**
 * ActionListener for the calculateEquity button.
 * 
 */
public class SimulationListener implements ActionListener
{    
    private PokerCalculator calc;
    private JTextField hand1;
    private JTextField hand2;
    private JTextArea output;
    
    public SimulationListener(PokerCalculator calc, JTextField hand1, JTextField hand2, JTextArea output)
    {
        this.calc = calc;
        this.hand1 = hand1;
        this.hand2 = hand2;
        this.output = output;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        try
        {
            calc.calculateEquity(hand1.getText().trim(), hand2.getText().trim());
            output.setText(calc.toString());
        }
        catch (RuntimeException e)
        {
            output.setText(e.getMessage());      
        }
    }

}
