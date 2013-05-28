

package poker.ui;

import java.awt.*;
import javax.swing.*;
import poker.Simulator;

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
        frame.setPreferredSize(new Dimension(400, 100));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
   

    private void createComponents(Container container)
    {
        container.add(createInputArea());
        JTextArea output = new JTextArea();
        output.setEditable(false);
        container.add(output, BorderLayout.SOUTH);
    }
    
    private JPanel createInputArea()
    {
        JPanel panel = new JPanel(new GridLayout(4,2));
        panel .add(new JLabel("Hand:"));
        panel.add(new JTextField());
        panel.add(new JLabel("Hand:"));
        panel.add(new JTextField());
        panel.add(new JLabel());
        panel.add(new JButton("Simulate"));
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

