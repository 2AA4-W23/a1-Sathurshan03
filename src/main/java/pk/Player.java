package pk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.*;

public class Player {
    String name;
    int gamesWon;
    int score;
    int numSkulls;
    int numWins;
    Strategy playerStrategy;
    List <Dice> myDice = new ArrayList<>(); 
    List <Dice> currentDice = new ArrayList<>(); 
    List <Dice> skullDice = new ArrayList<>();
    private static Logger LOG = LogManager.getLogger(Simulation.class);

    public Player(String name){
        this.name = name;
        gamesWon = 0;
        score = 0;
        numSkulls = 0;
        numWins = 0;
        playerStrategy = new Strategy(strategies.RANDOM);
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
        //cleaning memory before turn starts
        numSkulls = 0;
        myDice.clear();
        currentDice.clear();
        skullDice.clear();

        if("True".equals(System.getProperty("traceMode"))){
            LOG.trace("--" + name + " is rolling--");
        }

        //Initial Roll
        for (int j = 0; j < 8; j++)
        {
            myDice.add(new Dice());
        }
        currentDice = myDice;
        if("True".equals(System.getProperty("traceMode"))){
            logDice();
        }

        //Rerolls
        int numRolls = 1;
        boolean continueRoll = false;
        while(true)
        {
            //check if player can continue rolling or not
            if (continueTurn())
            {
                sortPlayerDice();
                continueRoll = playerStrategy.useStrategy(this, numRolls);
                if (continueRoll == false)
                {
                    //Player decided not to re-roll
                    break;
                }
            }
            else{
                break;
            }
        }

        calculateScore();
        
        if("True".equals(System.getProperty("traceMode"))){
            LOG.trace("-Final Values of the Dice-");
            logDice();
            LOG.trace(name +"'s score: " + score);
        }

    }
    private Boolean continueTurn(){
        //Check if turn ends. Turn ends when there are 3 or more skulls in player dice set
        //return Boolean: true if turn is not over, false if turn is over

        //Counts the number of skulls rolled and remove the skulls from the currentDice
        Iterator<Dice> iter = currentDice.iterator();
        while(iter.hasNext())
        {
            Dice die = (Dice)iter.next();
            if (die.getRollValue().equals(Faces.SKULL)){
                numSkulls++;
                skullDice.add(die);
                iter.remove();
            }
        }

        //check if turn is over by examining the number of skulls
        if (numSkulls >= 3)
        {
            if("True".equals(System.getProperty("traceMode"))){
            LOG.trace("Turn is over! More than 3 skulls were collected.");
            }
            return false;
        }
        else
        {
            return true;
        }
    }

    public void logDice(){
        //logs the 8 Dice information 
        sortPlayerDice();

        for (int m = 0; m < currentDice.size(); m++)
        {
            LOG.trace((m+1) + " " + currentDice.get(m).getRollValue());
        }
        for (Dice die:skullDice)
        {
            LOG.trace("- " + die.getRollValue());
        }
    }

    private void sortPlayerDice()
    {
        //Sorts the player dice based on the enum order
        Collections.sort(currentDice, new Comparator<Dice>() {
            @Override
            public int compare(Dice die1, Dice die2) {
                return die1.getRollValue().compareTo(die2.getRollValue());
            }});
    }

    private void calculateScore()
    {
        //Calculate the player's score at the end of their turn
        if (numSkulls >= 3)
        {
            score += 0;
        }
        
        else 
        {
            //Atleast three in a row combo
            int counter;
            //iterate through each face except for the skull
            for (int i = 0; i < 5; i++)
            {
                //count the number of dice that has that face
                counter = 0;
                for (Dice die:currentDice)
                {
                    if (die.getRollValue().equals(Faces.values()[i])){
                        counter ++;
                    }
                }
                //reward points based on the number of identical faces
                if (counter == 3)
                {
                    score += 100;
                }
                else if (counter == 4)
                {
                    score += 200;
                }
                else if (counter == 5)
                {
                    score += 500;
                }
                else if (counter == 6)
                {
                    score += 1000;
                }
                else if (counter == 7)
                {
                    score += 2000;
                }
                else if (counter >= 8)
                {
                    score += 4000;
                }

                //Reward points for rolling diamond and gold coins
                if (Faces.values()[i].equals(Faces.DIAMOND) || Faces.values()[i].equals(Faces.GOLD))
                {
                    score += counter * 100;
                }
            }
        }
    }
}
