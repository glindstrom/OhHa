

package poker;

/**
 * Calculates the pre-flop all-in equity for two Hold'em hands. 
 * 
 */
public class Simulator 
{
    private int wins;
    private int losses;
    private int ties;
    private int trials;
    private Hand holeCards1;
    private Hand holeCards2;
    private Deck deck;
    private Evaluator evaluator;
    
    /**
     * Class constructor.
     */
    public Simulator()
    {
        resetStats();
        this.deck = new Deck();
        this.trials = 50000;
        this.evaluator = new Evaluator();
    }
    
    public int getWins()
    {
        return this.wins;
    }
    
    public int getLosses()
    {
        return this.losses;
    }

    public int getTies()
    {
        return ties;
    }        

    public int getTrials()
    {
        return trials;
    }
    
    public double winPercentage()
    {
        return 1.0*this.wins/trials;
    }
    
    public void simulate(String holeCards1, String holeCards2)
    {
        resetStats();
        this.holeCards1 = new Hand(holeCards1);
        this.holeCards2 = new Hand(holeCards2);
        
        for (int i = 0; i < this.trials; i++)
        {
            this.deck.reset();
            removeHoleCardsFromDeck();
            this.deck.shuffle();
            Hand board = deal5cards();
            Hand h1 = Hand.mergeHands(board, this.holeCards1);
            Hand h2 = Hand.mergeHands(board, this.holeCards2);
            updateResults(h1, h2);
        }
    }
    
    public String printHand1()
    {
        return this.holeCards1.toString();
    }
   
    public String printHand2()
    {
        return this.holeCards2.toString();
    }
    
    public double equity1()
    {
        return winPercentage() + 0.5*tiePercentage();
    }
    
    public double equity2()
    {
        return lossPercentage() + 0.5*tiePercentage();
    }
    
    public double lossPercentage()
    {
        return 1.0*losses/trials;
    }

    public double tiePercentage()
    {
        return 1.0*ties/trials;
    }
    private void removeHoleCardsFromDeck()
    {
        for (Card c : this.holeCards1.getCards())
        {
            this.deck.remove(c);
        }
        for (Card c : this.holeCards2.getCards())
        {
            this.deck.remove(c);
        }
    }

    private Hand deal5cards()
    {
        Hand h = new Hand();
        for (int i = 0; i < 5; i++)
        {
            Card c = deck.dealCard();
            h.addCard(c);
        }
        return h;
    }

    private void updateResults(Hand h1, Hand h2)
    {
        Hand bestHand1 = this.evaluator.best5CardHand(h1);
        Hand bestHand2 = this.evaluator.best5CardHand(h2);
        int comparison = this.evaluator.compareHands(bestHand1, bestHand2);
        if (comparison == 1)
        {
            this.wins++;
        }
        else if (comparison == 2)
        {
            this.losses++;
        }
        else 
        {
            ties++;
        }
    }
    
    private void resetStats()
    {
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
    }
    
    public static void main(String[] args)
    {
        Simulator sim = new Simulator();
        sim.simulate("QdTs", "7c7d");
        System.out.println(sim.equity1());        
    }
}
