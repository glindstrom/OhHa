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
    }
    
    public int compareHands(Hand h1, Hand h2)
    {
        HandCategory hc1  = handCategory(h1);
        HandCategory hc2 = handCategory(h2);
        int comparison = hc1.compareTo(hc2);
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

    public HandCategory handCategory(Hand h)
    {
        calculateCardFrequencies(h);
        
        if (findFourOfAKind(h) >= 0)
        {
            return new Quads(Rank.intToRank(findFourOfAKind(h)));
        }
        if (findThreeOfAKind(h) >= 0 && findOnePair(h) >= 0)
        {
            return new FullHouse();
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
            return new StraightFlush(h.getCards().first());
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
        TreeSet<Card> cards = h.getCards();
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
        if (cards.get(0).getRank() == Rank.ACE && cards.get(1).getRank() == Rank.FIVE)
        {
            return true;
        }
        for (int i = 0; i < cards.size()-1; i++)
        {
            if (cards.get(i).getRank().ordinal() != cards.get(i+1).getRank().ordinal()-1)
            {
                return false;
            }
        }
        return true;
    }
        
    public static void main(String[] args)
    {
        Evaluator e = new Evaluator();
        Hand h1 = new Hand(new Card("Qh"), new Card("Qd"), new Card("Qc"), new Card("Qs"), new Card("2h"));
        Hand h2 = new Hand(new Card("Th"), new Card("Td"), new Card("Tc"), new Card("Ts"), new Card("2h"));
        System.out.println(e.compareHands(h1, h2));
    }

    private void calculateCardFrequencies(Hand h)
    {
        this.cardFrequencies = new int[13];
        for (Card c : h.getCards())
        {
            this.cardFrequencies[c.getRank().ordinal()]++;
        }
    }
}
