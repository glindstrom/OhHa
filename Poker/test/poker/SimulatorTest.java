/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poker;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SimulatorTest
{
    Simulator sim;
    
    @Before
    public void setUp()
    {
        sim = new Simulator();
    }
    @Test
    public void constructorSetsWinsToZero()
    {
        assertEquals(0, sim.getWins());
        assertEquals(0, sim.getLosses());
    }    
    
    @Test
    public void constructorSetsTiesToZero()
    {
        assertEquals(0, sim.getTies());
    }
    
    @Test
    public void stringRepresentationsOfHandsAreCorrect()
    {
        String hc1 = "AdAh";
        String hc2 = "KsKc";
        sim.simulate("AdAh", "KsKc");
        assertEquals(hc1, sim.printHand1());
        assertEquals(hc2, sim.printHand2());
    }
   
    @Test
    public void lossesWinsAndTiesSumToTrialsAfterSimulation()
    {
        sim.simulate("AdKc", "JhTs");
        assertEquals(sim.getTrials(), sim.getLosses() + sim.getWins() + sim.getTies());
    }
    
    @Test
    public void equityOfAAvsAAis50()
    {
        sim.simulate("AdAc", "AhAs");
        assertEquals(0.5, sim.equity1(), 0.02);
    }
    
    @Test
    public void equityOfJTsVs22isCorrect()
    {
        sim.simulate("5d5s", "2c2d");
        assertEquals(0.815, sim.equity1(), 0.02);
    }
    
    @Test
    public void twoSimulationsInRowWorks()
    {
        sim.simulate("AdAc", "AhAs");
        assertEquals(0.5, sim.equity1(), 0.02);
        sim.simulate("5d5s", "2c2d");
        assertEquals(0.815, sim.equity1(), 0.02);
    }
    
    @Test
    public void equitiesAddToOne()
    {
        sim.simulate("5h4d", "Tc7c");
        assertEquals(1.0, sim.equity1() + sim.equity2(), 0.000001);
    }
}
