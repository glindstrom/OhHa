

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

    Hand(String hand)
    {
        this.cards = new ArrayList();
        for (int i = 0; i < hand.length(); i+=2)
        {
            String card = String.valueOf(hand.charAt(i)) + hand.charAt(i+1); 
            Card c = new Card(card);
            this.cards.add(c);
        }
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
    

    List<Card> getCards()
    {
        return this.cards;
    }    
}
