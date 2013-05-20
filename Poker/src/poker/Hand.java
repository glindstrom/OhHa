

package poker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * A Hand object represents a five card poker hand.
 * @see Card
 */
public class Hand 
{
    private Set<Card> cards;
        
    public Hand()
    {
        this.cards = new HashSet();
    }

    Hand(Card... cards)
    {
        this.cards = new HashSet();
        this.cards.addAll(Arrays.asList(cards));
    }            
    
    /**
     * Adds a card to the Hand.
     * @param c The Card to be added.
     * @return Returns true if the card was successfully added, false otherwise.
     */
    public boolean addCard(Card c)
    {
        if (this.cards.size() == 5)
        {
            return false;
        }
        this.cards.add(c);
        return true;
    }
    

    Set<Card> getCards()
    {
        return new HashSet<>(this.cards);
    }    
}
