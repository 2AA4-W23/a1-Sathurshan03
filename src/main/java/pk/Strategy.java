package pk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

enum strategies {
    RANDOM
}

public class Strategy {

    strategies currentStrategy;

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

            //Output the results
            printReRollDice(pos);
            numRolls++;
            System.out.println("\n-Roll " + numRolls + "-");
            player.printDice();
            
            return true;
        }
        else{
            System.out.println("\nPlayer decided not to re-roll");
            return false;
        }
    }

    private void printReRollDice(List <Integer> pos)
    {
         //Print the specific dice that is getting rerolled
         System.out.print("\nDices ");
         for(Integer num: pos){
             System.out.print((num + 1) + " ");
         }
         System.out.println("are re-rolled");
    }
}
