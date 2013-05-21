package poker;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import poker.handCategories.*;

public class Evaluator
{

    //private int[] cardFrequencies;

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
        
        if (findFourOfAKind(h))
        {
            return new Quads(h);
        }
        if (findThreeOfAKind(h) && findOnePair(h))
        {
            return new FullHouse(h);
        }
        if (findThreeOfAKind(h))
        {
            return new ThreeOfAKind(h);
        }
        if (findTwoPair(h))
        {
            return new TwoPair(h);
        }
        if (findOnePair(h))
        {
            return new OnePair(h);
        }
        if (findFlush(h) && findStraight(h))
        {
            return new StraightFlush(h);
        }
        if (findFlush(h))
        {
            return new Flush(h);
        }
        if (findStraight(h))
        {
            return new Straight(h);
        }
       
        return new HighCard(h);
    }

    private boolean findFourOfAKind(Hand h)
    {
        return hasKind(4, h);
    }

    private boolean findThreeOfAKind(Hand h)
    {
        return hasKind(3, h);
    }

    private boolean findTwoPair(Hand h)
    {
        int numberOfPairs = 0;
        int [] cardFrequencies = calculateCardFrequencies(h);
        for (int i : cardFrequencies)
        {
            if (i == 2)
            {
                numberOfPairs++;
            }
        }
        return numberOfPairs == 2;
    }

    private boolean findOnePair(Hand h)
    {
        return hasKind(2, h);
    }

    private boolean hasKind(int n, Hand h)
    {
        int [] cardFrequencies = calculateCardFrequencies(h);
        for (int i = 0; i < cardFrequencies.length; i++)
        {
            if (cardFrequencies[i] == n)
            {
                return true;
            }
        }
        return false;
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

    public static int [] calculateCardFrequencies(Hand h)
    {
        int [] cardFrequencies = new int[13];
        for (Card c : h.getCards())
        {
            cardFrequencies[c.getRank().ordinal()]++;
        }
        return cardFrequencies;
    }
}
