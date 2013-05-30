

package poker;

import poker.ui.Gui;


public class Main 
{
    public static void main(String[] args)
    {
        Gui gui = new Gui(new Simulator());
        javax.swing.SwingUtilities.invokeLater(gui);
    }
}
