

package poker.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import poker.calculator.PokerCalculator;

/**
 * ActionListener for the calculateEquity button.
 * 
 */
public class SimulationListener implements ActionListener
{  
    /**
     * the poker calculator which calculated the equity
     */
    private PokerCalculator calc;
    /**
     * the text field for the first hand
     */
    private JTextField hand1;
    /**
     * the text field for the second hand
     */
    private JTextField hand2;
    /**
     * the text area where the output will be displayed
     */
    private JTextArea output;
    
    /**
     * Class constructor.
     * @param calc a poker calculator
     * @param hand1 a text field for the first hand
     * @param hand2 a text field for the second hand
     * @param output text area where the results are printed
     */
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
