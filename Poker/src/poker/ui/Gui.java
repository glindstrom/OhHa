

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
    
    /**
     * Class constructor.
     * @param sim the simulator to be used
     */
    public Gui(Simulator sim)
    {
        this.sim = sim;
        initSimulator();
    }
    
    @Override
    public void run()
    {
        frame = new JFrame("Texas Hold'em Equity Calculator");
        frame.setPreferredSize(new Dimension(405, 210));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.setJMenuBar(createMenuBar());
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
        panel .add(new JLabel("Hand: "));
        
        JTextField hand1 = new JTextField();
        panel.add(hand1);
        panel.add(new JLabel("Hand: "));
        JTextField hand2 = new JTextField();
        panel.add(hand2);
        
        panel.add(new JLabel());
        
        addSimulateButton(panel, hand1, hand2, output);
        
        panel.add(new JLabel());
        panel.add(new JLabel());
        
        return panel;
    }    
    
    /**
     * Creates a menu bar.
     * @return a menu bar
     */
    private JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();        
        menuBar.add(createFileMenu());
        menuBar.add(createOptionsMenu());                                               
        return menuBar;
    }
    
    /**
     * Creates a file menu.
     * @return a menu
     */
    private JMenu createFileMenu()
    {
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(new ExitItemListener());
        return fileMenu;
    }
    
    /**
     * Creates an options menu
     * @return a menu
     */
    private JMenu createOptionsMenu()
    {
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem trialsItem = new JMenuItem("Trials");
        trialsItem.addActionListener(new TrialsItemListener(frame, sim));
        optionsMenu.add(trialsItem);        
        return optionsMenu;
    }             
    
    /**
     * Adds a simulate button to the specified panel.
     * @param panel the panel to which the button is added
     * @param hand1 a text field for the first hand in the simulation
     * @param hand2 a text field for the second hand in the simulation
     * @param output the text area in which the result is displayed
     */
    private void addSimulateButton(JPanel panel, JTextField hand1, JTextField hand2, JTextArea output)
    {
        JButton simulateButton = new JButton("Simulate");
        SimulationListener listener = new SimulationListener(this.sim, hand1, hand2, output);
        simulateButton.addActionListener(listener);
        panel.add(simulateButton);
    }

    /**
     * Sets the default number of trials from file, if such a number exists.
     */
    private void initSimulator()
    {
        try
        {
            Settings settings = new Settings();
            this.sim.setTrials(settings.readInt());
        }
        catch (Exception e)
        {            
        }        
    }
}

