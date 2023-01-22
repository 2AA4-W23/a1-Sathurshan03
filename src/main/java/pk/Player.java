package pk;

public class Player {
    String name;
    int gamesWon;
    int score;
    int numWins;
    Cards currentCard;
    DiceCup diceCup;
    Strategy playerStrategy;
    Log log = new Log();

    public Player(String name, String strategy){
        this.name = name;
        gamesWon = 0;
        score = 0;
        numWins = 0;

        if (strategy.toLowerCase().equals("random"))
        {
            log.logMessage(name + " is using strategy: RANDOM");
            playerStrategy = new Strategy(strategies.RANDOM);
        }
        else if (strategy.toLowerCase().equals("combo"))
        {
            log.logMessage(name + " is using strategy: COMBO");
            playerStrategy = new Strategy(strategies.COMBO);
        }
        else{
            //default to random strategy if invalid input
            log.logMessage(name + " is using strategy: RANDOM");
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

    public void turn(Cards card){
        //set the player drawed card for this turn
        currentCard = card;
        playerStrategy.setRoundCard(currentCard);

        //cleaning diceCup before turn starts
        diceCup = new DiceCup(currentCard);

        log.logMessage("--" + name + " is rolling--");

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
                    log.logMessage(name + " decided not to re-roll");
                    
                }
            }
            else{
                break;
            }
        }

        diceCup.logFinalDice();

        score += diceCup.calculateDiceCupScore();
        log.logMessage(name +"'s score: " + score);
    }
}
