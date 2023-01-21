package pk;

import org.apache.logging.log4j.*;

public class Player {
    String name;
    int gamesWon;
    int score;
    int numWins;
    DiceCup diceCup;
    Strategy playerStrategy;
    private static Logger LOG = LogManager.getLogger(Simulation.class);

    public Player(String name, String strategy){
        this.name = name;
        gamesWon = 0;
        score = 0;
        numWins = 0;
        diceCup = new DiceCup();

        if (strategy.toLowerCase().equals("random"))
        {
            if("True".equals(System.getProperty("traceMode"))){
                LOG.trace(name + " is using strategy: RANDOM");
            }
            playerStrategy = new Strategy(strategies.RANDOM);
        }
        else if (strategy.toLowerCase().equals("combo"))
        {
            if("True".equals(System.getProperty("traceMode"))){
                LOG.trace(name + " is using strategy: COMBO");
            }
            playerStrategy = new Strategy(strategies.COMBO);
        }
        else{
            //default to random strategy if invalid input
            if("True".equals(System.getProperty("traceMode"))){
                LOG.trace(name + " is using strategy: RANDOM");
            }
            playerStrategy = new Strategy(strategies.RANDOM);
        }
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public int getNumWins(){
        return numWins;
    }

    public void increaseNumWins()
    {
        numWins++;
    }

    public void resetScore(){
        score = 0;
    }

    public void turn(){
        //cleaning diceCup before turn starts
        diceCup = new DiceCup();

        if("True".equals(System.getProperty("traceMode"))){
            LOG.trace("--" + name + " is rolling--");
        }

        //Initial Roll
        diceCup.intialRoll();

        //Rerolls
        int numRolls = 1;
        boolean continueRoll = true;
        while(continueRoll)
        {
            //check if player can continue rolling or not
            if (diceCup.underThreeSkull())
            {
                diceCup.sortPlayerDice();
                numRolls++;
                continueRoll = playerStrategy.useStrategy(diceCup, numRolls);
                if (!continueRoll)
                {
                    //Player decided not to re-roll
                    if("True".equals(System.getProperty("traceMode"))){
                        LOG.trace(name + " decided not to re-roll");
                    }
                }
            }
            else{
                break;
            }
        }

        diceCup.logFinalDice();

        score += diceCup.calculateDiceCupScore();
        if("True".equals(System.getProperty("traceMode"))){
            LOG.trace(name +"'s score: " + score);
        }
    }
}
