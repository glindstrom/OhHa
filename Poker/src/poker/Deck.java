

package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A standard 52-card deck.
 * 
 */
public class Deck 
{
    private List<Card> cards;
    
    /*
     * Creates an unshuffled deck ordered by suit and rank.
     */
    public Deck()
    {
        this.cards = new ArrayList();
        initDeck();
    }
    
    /**
     * Returns the number of cards in the deck.
     * @return the number of cards in the deck
     */
    public int size()
    {
        return this.cards.size();        
    }

    /**
     * Returns a card from the top of the deck.
     * @return a card from the top of the deck.
     */
    public Card dealCard()
    {
        return this.cards.remove(0);
    }

    /**
     * Removes the specified card from the deck.
     * @param c the card to be removed
     * @return true if the card was removed, false if the deck did not contain
     * the specified card
     */
    public boolean remove(Card c)
    {
        return this.cards.remove(c);
    }

    /**
     * Shuffles the cards in the deck.
     */
    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    /**
     * Puts back all cards to the deck and orders them by suit and rank.
     */
    public void reset()
    {
        initDeck();
    }

    /**
     * Clears the deck and adds all cards in suit and rank order. The suits are
     * ordered from spades to clubs and the ranks from ace to deuce.
     */
    private void initDeck()
    {
        this.cards.clear();
        for (Suit suit : Suit.values())
        {
            for (Rank rank : Rank.values())
            {
                Card c = new Card(rank, suit);
                this.cards.add(c);
            }
        }
    }

}
