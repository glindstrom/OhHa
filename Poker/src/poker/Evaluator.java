package poker;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import poker.handCategories.*;

class Evaluator
{

    private int[] cardFrequencies;

    public Evaluator()
    {
        this.cardFrequencies = new int[13];
    }

    HandCategory handCategory(Hand h)
    {
        for (Card c : h.getCards())
        {
            this.cardFrequencies[c.getRank().ordinal()]++;
        }
        if (findFourOfAKind(h) >= 0)
        {
            return new FourOfAKind();
        }
        if (findThreeOfAKind(h) >= 0)
        {
            return new ThreeOfAKind();
        }
        if (findTwoPair(h) >= 0)
        {
            return new TwoPair();
        }
        if (findOnePair(h) >= 0)
        {
            return new OnePair();
        }
        if (findFlush(h) && findStraight(h))
        {
            return new StraightFlush();
        }
        if (findFlush(h))
        {
            return new Flush();
        }
        if (findStraight(h))
        {
            return new Straight();
        }
       
        return new HighCard();
    }

    private int findFourOfAKind(Hand h)
    {
        return hasKind(4, 0);
    }

    private int findThreeOfAKind(Hand h)
    {
        return hasKind(3, 0);
    }

    private int findTwoPair(Hand h)
    {
        int i = hasKind(2, 0);
        return i < 0 ? i : hasKind(2, i + 1);
    }

    private int findOnePair(Hand h)
    {
        return hasKind(2, 0);
    }

    private int hasKind(int n, int startIndex)
    {
        for (int i = startIndex; i < this.cardFrequencies.length; i++)
        {
            if (this.cardFrequencies[i] == n)
            {
                return i;
            }
        }
        return -1;
    }

    private boolean findFlush(Hand h)
    {
        TreeSet<Card> cards = (TreeSet<Card>) h.getCards();
        Card card = cards.pollFirst();
        for (Card c : cards)
        {
            if (c.getSuit() != card.getSuit())
            {
                return false;
            }
        }
        return true;
    }

    private boolean findStraight(Hand h)
    {
        List<Card> cards = new ArrayList<>();
        cards.addAll(h.getCards());
        for (int i = 0; i < cards.size()-1; i++)
        {
            if (cards.get(i).getRank().ordinal() != cards.get(i+1).getRank().ordinal()-1)
            {
                return false;
            }
        }
        return true;
    }
        
}
