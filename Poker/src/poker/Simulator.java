

package poker;

/**
 * Calculates the pre-flop all-in equity for two Hold'em hands using a Monte Carlo simulation. 
 * 
 */
public class Simulator 
{
    /**
     * The number of times hand1 won.
     */
    private int wins;
    /**
     * The number of times hand1 lost.
     */
    private int losses;
    /**
     * The number of times the hands tied.
     */
    private int ties;
    /**
     * The number of random boards dealt.
     */
    private int trials;
    /**
     * The first hand.
     */
    private Hand holeCards1;
    /**
     * The second hand.
     */
    private Hand holeCards2;
    /**
     * A deck of cards.
     */
    private Deck deck;
    /**
     * A hand evaluator.
     */
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
    
    /**
     * Calculates the winning percentage of the first hand.
     * @return the winning percentage expressed in decimal format
     */
    public double winPercentage()
    {
        return 1.0*this.wins/trials;
    }
    
    /**
     * Calculates the loss percentage of the first hand.
     * @return the loss percentage expressed in decimal format
     */
     public double lossPercentage()
    {
        return 1.0*losses/trials;
    }
     
    /**
     * Calculates the tie percentage.
     * @return the tie percentage expressed in decimal format
     */ 
    public double tiePercentage()
    {
        return 1.0*ties/trials;
    }
    
    /**
     * Calculates the equity of two hands.
     * @param hand1 string expression of the first hand
     * @param hand2 string expression of the second hand
     */
    public void simulate(String hand1, String hand2)
    {
        checkHand(hand1);
        checkHand(hand2);
        resetStats();
        this.holeCards1 = new Hand(hand1);
        this.holeCards2 = new Hand(hand2);
        
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
    
    /**
     * Calculates the equity of the first hand.
     * Equity is defined as winning percentage + tie percentage / 2. For example,
     * if the pot size is 100 and the equity is 60 %, it means that the expected
     * stack size is 0.6*100=60.
     * @return the equity expressed as a decimal number
     */
    public double equity1()
    {
        return winPercentage() + 0.5*tiePercentage();
    }
    
    /**
     * Calculates the equity of the second hand.
     * @return the equity expressed as a decimal number
     */
    public double equity2()
    {
        return lossPercentage() + 0.5*tiePercentage();
    }
    
    /**
     * Removes the hole cards from the deck.
     */
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
    
    /**
     * Deals the board.
     * @return a 5-card hand
     */
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

    /**
     * Updates the counters after performed simulation.
     * @param h1 The board + hole cards of the first hand.
     * @param h2 The board + hole cards of the second hand.
     */
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
    
    /**
     * Sets all counters to zero.
     */
    private void resetStats()
    {
        this.wins = 0;
        this.losses = 0;
        this.ties = 0;
    }
    
    /**
     * Checks that the hand has correct length.
     * @param hand a string representation of a hand.
     */
    private void checkHand(String hand)
    {
        if (hand.length() != 4)
        {
            throw new IllegalArgumentException(hand + " is not a valid hand - expected 2 cards but got " + hand.length()/2);
        }
    }

    @Override
    public String toString()
    {
        String [] stringArray = {"Hand", "Equity", "Wins", "Ties",
            this.printHand1(), decimalToPercentageString(this.equity1()), Integer.toString(wins) ,Integer.toString(ties), 
            this.printHand2(), decimalToPercentageString(this.equity2()), Integer.toString(losses), Integer.toString(ties)
        };
        String s = "";
        for (int i = 0; i < stringArray.length; i++)
        {
            if ((i+1) % 4 != 0)
            {
                s +=  String.format("%-15s", stringArray[i]);
            }
            else
            {
                s += stringArray[i] + "\n";
            }
        }
        return s;
    }
    
    private String decimalToPercentageString(double d)
    {
        return String.format("%.2f", 100*d) + "%";
    }
    
    public static void main(String[] args)
    {
//        int i = 183;
//        System.out.print(String.format("%-15s", "Hand"));
//        System.out.print(String.format("%-15s", "Equity"));
//        System.out.print(String.format("%-15s", "Wins"));
//        System.out.println(String.format("%-15s", "Ties"));
//        System.out.print(String.format("%-15s", "AdKc"));
//        String per = String.format("%.2f", 100*0.574268) + " %";
//        System.out.print(String.format("%-15s", per));
//        System.out.print(String.format("%-15s", Integer.toString(i)));
        Simulator sim = new Simulator();
        sim.simulate("AdKc", "6c5d");
        System.out.println(sim.toString());
    }
    
}
