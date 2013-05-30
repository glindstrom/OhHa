

package poker.ui;

import java.awt.*;
import javax.swing.*;
import poker.PokerCalculator;

/**
 * Provides a graphical user interface for a poker simulator.
 * 
 */
public class Gui implements Runnable
{
    private JFrame frame;
    private PokerCalculator calc;
    
    /**
     * Class constructor.
     * @param calc the pokerCalculator to be used
     */
    public Gui(PokerCalculator calc)
    {
        this.calc = calc;
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

    /**
     * Adds components to the contentPane.
     * @param container the container to which the components are added
     */
    private void createComponents(Container container)
    {
        JTextArea output = new JTextArea(6,1);
        Font font = new Font("Courier new",Font.PLAIN, 12);
        output.setFont(font);
        output.setEditable(false);
        container.add(createInputArea(output));       
        container.add(output, BorderLayout.SOUTH);
    }
    
    /**
     * Creates the input area.
     * @param output the output area
     * @return a JPanel
     */
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
        SimulationListener listener = new SimulationListener(this.calc, hand1, hand2, output);
        simulateButton.addActionListener(listener);
        panel.add(simulateButton);
        panel.add(new JLabel());
        panel.add(new JLabel());
        return panel;
    }    
}

