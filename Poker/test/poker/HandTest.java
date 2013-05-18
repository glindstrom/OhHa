package poker;

import java.util.List;
import java.util.ArrayList;
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
    public void cardsInHandMatchesConstructor()
    {
        Hand h = new Hand("7h3cJhQsKc");
        List<Card> cards = h.getCards();
        List<Card> compareTocards = createFiveCards();
        for (int i = 0; i < cards.size(); i++)
        {
            assertEquals(compareTocards.get(i), cards.get(i));
        }
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
