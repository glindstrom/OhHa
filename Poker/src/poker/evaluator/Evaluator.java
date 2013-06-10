package poker.evaluator;

import java.util.List;
import poker.deck.Card;
import poker.handCategories.*;
/**
 * Determines the winner of two poker hands. 
 * 
 */
public class Evaluator
{

    /*
     * Class constructor.
     */
    public Evaluator()
    {
    }
    /**
     * Evaluates two poker hands at showdown. If only one hand contains five cards, then that hand wins.
     * If neither hand contains five cards they tie.
     * @param h1 the first hand to be compared
     * @param h2 the second hand to be compared
     * @return Returns 1 if h1 wins, 2 if h2 wins and 0 if the hands tie.
     */
    public int compareHands(Hand h1, Hand h2)
    {
        if (h1.size() != 5 && h2.size() != 5)
        {
            return 0;
        }
        if (h1.size() == 5 && h2.size() != 5)
        {
            return 1;
        }
        if (h2.size() == 5 && h1.size() != 5)
        {
            return 2;
        }
        int comparison = HandCategory.extractHandCategory(h1).compareTo(HandCategory.extractHandCategory(h2));
        if (comparison < 0)
        {
            return 1;
        }
        if (comparison > 0)
        {
            return 2;
        }
        return 0;
    }

    /**
     * Returns the best 5-card poker hand you can make from a 7-card hand.
     * @param h the 7-card hand to be evaluated
     * @return the best 5-card hand you can make from a 7-card hand
     */
    public Hand best5CardHand(Hand h)
    {        
        return best5CardHandOutOfNcards(h, 7);
    }

    /**
     * Returns the best 5-card poker hand you can make from a n-card hand.
     * @param h the hand to be evaluated
     * @param n the number of cards in the hand
     * @return the best 5-card hand you can make from the given hand
     */
    private Hand best5CardHandOutOfNcards(Hand h, int n)
    {   
        Hand bestHand = new Hand();
        List<Card> cards = h.getCards();
        for (int i = 0; i < n ; i++)
        {
            for (int j = i+1; j < n; j++)
            {
                for (int k = j+1; k < n; k++)
                {
                    for (int l = k+1; l < n; l++)
                    {
                        for (int m = l+1; m < n; m++)
                        {
                            Hand comparableHand = new Hand();
                            comparableHand.addCard(cards.get(i));
                            comparableHand.addCard(cards.get(j));
                            comparableHand.addCard(cards.get(k));
                            comparableHand.addCard(cards.get(l));
                            comparableHand.addCard(cards.get(m));
                            if (compareHands(comparableHand, bestHand) == 1)
                            {
                                bestHand = comparableHand;
                            }
                        }
                    }
                }
            }
        }
        return bestHand;
    }        
}
