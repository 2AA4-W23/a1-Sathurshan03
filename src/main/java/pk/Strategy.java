package pk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.*;

enum strategies {
    RANDOM
}

public class Strategy {

    strategies currentStrategy;
    private static Logger LOG = LogManager.getLogger(Simulation.class);

    public Strategy(strategies currentStrategy ){
        this.currentStrategy = currentStrategy;
    }

    public void changeStrategy(strategies newStrategy){
        currentStrategy = newStrategy;
    }

    public boolean useStrategy(Player player, int numRolls)
    {
        //Run the logic of the player's strategy
        if (currentStrategy.equals(strategies.RANDOM)){
            return runRandomStrategy(player, numRolls);
        }
        return false;
    }
    
    private boolean runRandomStrategy(Player player, int numRolls)
    {
        //The logic of re-rolling for the RANDOM strategy

        int numActiveDice = player.currentDice.size();

        //Reroll random number of dice that are not skulls
        Random randomNumber = new Random();
        int numReRoll = randomNumber.nextInt(numActiveDice);

        //If numReRoll is 0, no dice are reroll, so we end turn
        //If numReRoll is not 0, we add 1 because we must roll atleast 2 dice at a time
        if (numReRoll != 0)
        {
            numReRoll++;
            List <Integer> pos = new ArrayList<>(); 
            
            //randomly find the positions of dice to re-roll
            for(int k = 0; k < numReRoll; )
            {
                int index = randomNumber.nextInt(numActiveDice);
                //ensure no duplicates so only unique dice are re-rolled
                if (!(pos.contains((Integer)index))){
                    pos.add((Integer)index);
                    k++;
                }
            }
            Collections.sort(pos); 

            //Re-roll the selected pos dice 
            for (Integer index: pos){
                player.currentDice.get(index).roll();
            }

            //Log the results
            numRolls++;
            if("True".equals(System.getProperty("traceMode"))){
                logReRollDice(pos);
                LOG.trace("-Roll " + numRolls + "-");
                player.logDice();
            }
            
            return true;
        }
        else{
            if("True".equals(System.getProperty("traceMode"))){
                LOG.trace(player.name + " decided not to re-roll");
            }
            return false;
        }
    }

    private void logReRollDice(List <Integer> pos)
    {
         //Log the specific dice that is getting rerolled
         String posOutput = "";
         for(Integer num: pos){
             posOutput += (num + 1) + " ";
         }
         LOG.trace("Dices " + posOutput + " are re-rolled");
    }
}
