

package poker;

import poker.ui.Gui;

/**
 * Starts the execution of the Poker Equity Calculator.
 * 
 */
public class Main 
{
    public static void main(String[] args)
    {
        Gui gui = new Gui(new Simulator());
        javax.swing.SwingUtilities.invokeLater(gui);
    }
}
