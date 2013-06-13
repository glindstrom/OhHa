
package poker.calculator;

/**
 * The user of this interface can calculate the equity of two poker hands.
 * 
 */
public interface PokerCalculator
{
    /**
     * Calculates the equity of two hands.
     * Equity is defined as winning percentage + tie percentage / 2. For example,
     * if the pot size is 100 and the equity is 60 %, it means that the expected
     * stack size is 0.6*100=60.
     * @param hand1 string expression of the first hand
     * @param hand2 string expression of the second hand
     */
    void calculateEquity(String hand1, String hand2);
    
     /**
     * Returns the equity of the first hand.
     * @return the equity expressed as a decimal number
     */
    double equity1();
    
    /**
     * Returns the equity of the second hand.
     * @return the equity expressed as a decimal number
     */
     double equity2();
}
