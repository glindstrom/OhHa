package poker;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class HandTest
{

    @Test
    public void addingCardSuccessfullyReturnsTrue()
    {
        Hand h = new Hand();
        assertTrue(h.addCard(new Card("5d")));
    }

    @Test
    public void addingMoreThanFiveCardsReturnsFalse()
    {
        Hand h = new Hand();
        addFiveCardsToHand(h);
        Card c6 = new Card("As");
        assertFalse(h.addCard(c6));
    }
    
    @Test
    public void addedCardIsInHand()
    {
        Hand h = new Hand();
        Card c = new Card("4h");
        h.addCard(c);
        assertTrue(h.getCards().contains(c));
    }

    @Test
    public void cardInHandMatchesConstructor()
    {
        Card c = new Card("9c");
        Hand h = new Hand(c);
        assertTrue(h.getCards().contains(c));
    }

    private void addFiveCardsToHand(Hand h)
    {
        List<Card> cards = createFiveCards();
        for (Card c : cards)
        {
            h.addCard(c);           
        }
    }

    private List<Card> createFiveCards()
    {
        List<Card> cards = new ArrayList();
        Card c1 = new Card("7h");
        Card c2 = new Card("3c");
        Card c3 = new Card("Jh");
        Card c4 = new Card("Qs");
        Card c5 = new Card("Kc");
        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        return cards;
    }
}
