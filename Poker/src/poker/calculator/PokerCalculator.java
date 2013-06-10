
package poker.calculator;

/**
 * The user of this interface can calculate the equity of two poker hands.
 * 
 */
public interface PokerCalculator
{
    /**
     * Calculates the equity of two hands.
     * @param hand1 string expression of the first hand
     * @param hand2 string expression of the second hand
     */
    void calculateEquity(String hand1, String hand2);
}
