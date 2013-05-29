

package poker.ui;

import java.awt.*;
import javax.swing.*;
import poker.Simulator;

/**
 * Provides a graphical user interface for a poker simulator.
 * 
 */
public class Gui implements Runnable
{
    private JFrame frame;
    private Simulator sim;
    public Gui(Simulator sim)
    {
        this.sim = sim;
    }
    

    @Override
    public void run()
    {
        frame = new JFrame("Texas Hold'em Equity Calculator");
        frame.setPreferredSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
   

    private void createComponents(Container container)
    {
        JTextArea output = new JTextArea(6,1);
        output.setEditable(false);
        container.add(createInputArea(output));       
        container.add(output, BorderLayout.SOUTH);
    }
    
    private JPanel createInputArea(JTextArea output)
    {
        JPanel panel = new JPanel(new GridLayout(4,2));
        panel .add(new JLabel("Hand:"));
        JTextField hand1 = new JTextField();
        panel.add(hand1);
        panel.add(new JLabel("Hand:"));
        JTextField hand2 = new JTextField();
        panel.add(hand2);
        panel.add(new JLabel());
        JButton simulateButton = new JButton("Simulate");
        SimulationListener listener = new SimulationListener(this.sim, hand1, hand2, output);
        simulateButton.addActionListener(listener);
        panel.add(simulateButton);
        panel.add(new JLabel());
        panel.add(new JLabel());
        return panel;
    }
    
    public static void main(String[] args)
    {
        Gui gui = new Gui(new Simulator());
        javax.swing.SwingUtilities.invokeLater(gui);
    }
}

