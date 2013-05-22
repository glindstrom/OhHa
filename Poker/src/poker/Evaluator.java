package poker;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import poker.handCategories.*;

public class Evaluator
{

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
    
//    public Hand best5CardHand(Hand h)
//    {
//        return best5CardHandOutOfNcards(h, 7);
//    }

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
        
    public static int [] calculateCardFrequencies(Hand h)
    {
        int [] cardFrequencies = new int[13];
        for (Card c : h.getCards())
        {
            cardFrequencies[c.getRank().ordinal()]++;
        }
        return cardFrequencies;
    }

//    private Hand best5CardHandOutOfNcards(Hand h, int n)
//    {   Hand bestHand = new Hand();
//        ArrayList<Card> cards = new ArrayList();
//        cards.addAll(h.getCards());
//        for (int i = 0; i < n ; i++)
//        {
//            for (int j = i+1; j < n; j++)
//            {
//                for (int k = j+1; k < n; k++)
//                {
//                    for (int l = k+1; l < n; l++)
//                    {
//                        for (int m = l+1; m < n; m++)
//                        {
//                            
//                        }
//                    }
//                }
//            }
//        }
//    }
}
