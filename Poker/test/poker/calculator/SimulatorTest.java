
package poker.calculator;

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
        sim.calculateEquity("AdAh", "KsKc");
        assertEquals(hc1, sim.hand1ToString());
        assertEquals(hc2, sim.hand2ToString());
    }
   
    @Test
    public void lossesWinsAndTiesSumToTrialsAfterSimulation()
    {
        sim.calculateEquity("AdKc", "JhTs");
        assertEquals(sim.getTrials(), sim.getLosses() + sim.getWins() + sim.getTies());
    }
    
    @Test
    public void equityOfAAvsAAis50()
    {
        sim.calculateEquity("AdAc", "AhAs");
        assertEquals(0.5, sim.equity1(), 0.02);
    }
    
    @Test
    public void equityOfJTsVs22isCorrect()
    {
        sim.calculateEquity("5d5s", "2c2d");
        assertEquals(0.815, sim.equity1(), 0.02);
    }
    
    @Test
    public void twoSimulationsInRowWorks()
    {
        sim.calculateEquity("AdAc", "AhAs");
        assertEquals(0.5, sim.equity1(), 0.02);
        sim.calculateEquity("5d5s", "2c2d");
        assertEquals(0.815, sim.equity1(), 0.02);
    }
    
    @Test
    public void equitiesAddUpToOne()
    {
        sim.calculateEquity("5h4d", "Tc7c");
        assertEquals(1.0, sim.equity1() + sim.equity2(), 0.000001);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void tooManyCardsLeadsToException()
    {
        sim.calculateEquity("AcKsQ", "AsKd");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void faultyCardLeadsToException()
    {
        sim.calculateEquity("Ac5b", "Kc7s");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void sameCardInBothHandsLeadsToException()
    {
        sim.calculateEquity("Jc9s", "Jc8h");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void fewerThanOneTrialsLeadsToException()
    {
        sim.setTrials(0);
    }
}
