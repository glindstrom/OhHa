

package poker;

import java.util.ArrayList;
import java.util.List;
/**
 * A Hand object represents a five card poker hand.
 * @see Card
 */
public class Hand 
{
    private List<Card> cards;
        
    public Hand()
    {
        this.cards = new ArrayList();
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
}
