

package poker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
/**
 * A Hand object represents a poker hand containing at most seven cards.
 * @see Card
 */
public class Hand 
{
    private Set<Card> cards;
        
    public Hand()
    {
        this.cards = new HashSet();
    }
    /**
     * Creates a hand containing the specified cards.
     * @param cards The cards to be included in the hand.
     */
    Hand(Card... cards)
    {
        this.cards = new HashSet();
        this.cards.addAll(Arrays.asList(cards));
    }            
    
    /**
     * Adds a card to the Hand. At most seven cards can be added to the hand.
     * @param c The Card to be added.
     * @return Returns true if the card was successfully added, false otherwise.
     */
    public boolean addCard(Card c)
    {
        if (this.cards.size() == 7)
        {
            return false;
        }
        return this.cards.add(c);        
    }
    
    /**
     * Returns the cards in the hand.
     * @return A shallow copy of the set of cards.
     */
    public TreeSet<Card> getCards()
    {
        return new TreeSet<>(this.cards);
    }  
    
    /**
     * Returns number of cards in hand.
     * @return An integer denoting the number of cards in the hand. 
     */
    public int size()
    {
        return this.size();
    }
}
