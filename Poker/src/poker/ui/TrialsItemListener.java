package poker.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import poker.Simulator;

/**
 * ActionListener for the trials option.
 * 
 */
public class TrialsItemListener implements ActionListener
{
    /**
     * the frame over which the dialogs will appear
     */
    JFrame frame;
    /**
     * the simulator for which the number of trials is set
     */
    Simulator sim;
    
    /**
     * Constructs an action listener operating on the specified frame and simulator.
     * @param frame the frame over which the dialogs will appear
     * @param sim the simulator for which the number of trials is set
     */
    public TrialsItemListener(JFrame frame, Simulator sim)
    {
        this.frame = frame;
        this.sim = sim;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        int numberOfTrials = askInt();
        if (numberOfTrials != 0)
        {
            sim.setTrials(numberOfTrials);
        }
    }

    /**
     * Returns an integer specified by the user.
     * @return an integer in the interval [1000, 500000]
     */
    private int askInt()
    {
        boolean inputValid = false;
        String s;
        int trials = 0;
        do
        {
            s = JOptionPane.showInputDialog(frame, "Trials:", sim.getTrials());
            if (s == null)
            {
                return trials;
            }
            try
            {
                trials = Integer.parseInt(s);
                if (trials < 1000 || trials > 500000 )
                {
                    JOptionPane.showMessageDialog(frame, "Please enter a number between 1000 and 500000.", "Invalid number", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    inputValid = true;
                }
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Invalid input", JOptionPane.ERROR_MESSAGE);
            }
            
        } while (!inputValid);
        return trials;
    }
}
