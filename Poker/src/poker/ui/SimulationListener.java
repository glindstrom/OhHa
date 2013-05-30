

package poker.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import poker.Simulator;

/**
 * ActionListener for the simulate button.
 * 
 */
public class SimulationListener implements ActionListener
{
    private Simulator sim;
    private JTextField hand1;
    private JTextField hand2;
    private JTextArea output;
    
    public SimulationListener(Simulator sim, JTextField hand1, JTextField hand2, JTextArea output)
    {
        this.sim = sim;
        this.hand1 = hand1;
        this.hand2 = hand2;
        this.output = output;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        try
        {
            sim.simulate(hand1.getText().trim(), hand2.getText().trim());
            output.setText(sim.toString());
        }
        catch (RuntimeException e)
        {
            output.setText(e.getMessage());      
        }
    }

}
