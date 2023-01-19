package pk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.*;

enum strategies {
    RANDOM, COMBO
}

public class Strategy {

    strategies currentStrategy;
    private static Logger LOG = LogManager.getLogger(Simulation.class);
    ArrayList<Integer> posNotCombo;

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
        else if (currentStrategy.equals(strategies.COMBO)){
            return runComboStrategy(player, numRolls);
        }
        return false;
    }
    
    private boolean runRandomStrategy(Player player, int numRolls)
    {
        //The logic of re-rolling for the RANDOM strategy

        int numActiveDice;
        if (currentStrategy.equals(strategies.COMBO))
        {
            //if the strategy is combo, then set the size to the # of dices that are not combos
            numActiveDice = posNotCombo.size();
        }
        else{
            numActiveDice = player.currentDice.size();
        }

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
                int index;
                if (currentStrategy.equals(strategies.COMBO))
                {
                    index = randomNumber.nextInt(player.currentDice.size());
                    //ensure no duplicates so only unique dice are re-rolled
                    //AND ensure position is in posNotCombo so combo dices are not re-rolled
                    if (!(pos.contains((Integer)index)) && posNotCombo.contains((Integer)index)){
                        pos.add((Integer)index);
                        k++;
                    }
                }
                else{
                    index = randomNumber.nextInt(numActiveDice);
                     //ensure no duplicates so only unique dice are re-rolled
                    if (!(pos.contains((Integer)index))){
                        pos.add((Integer)index);
                        k++;
                    }
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
            return false;
        }
    }

    private boolean runComboStrategy(Player player, int numRolls)
    {
        //The logic of re-rolling for the COMBO strategy
        //The goal is to only re-roll the dice that do not already have a combo

        //Find the number of dice that are of each fice
        int numFace[] = new int[5]; //5 non-skull faces
        for(Dice activeDice:player.currentDice)
        {
            int index = activeDice.rollValue.ordinal();
            numFace[index] ++;
        }

        //Find any Faces that has 3 or more re-occurences
        ArrayList<Faces> comboFaces = new ArrayList<>();
        posNotCombo = new ArrayList<>();
        for (int i = 0; i < numFace.length; i++){
            if (numFace[i] >= 3){
                Faces face = Faces.values()[i];
                comboFaces.add(face);
            }
        }
        
        //if there are no combos, run random strategy for this turn
        Boolean continueTurn;
        if (comboFaces.size() == 0){
            currentStrategy = strategies.RANDOM;
            continueTurn = runRandomStrategy(player, numRolls);
            currentStrategy = strategies.COMBO;

        }
        else{

            //Do not re-roll if all active dices are part of a combo
            if(posNotCombo.size() == 0)
            {
                return false;
            }

            //Find the positions of dices that are not combo dices
            for (int i = 0; i<player.currentDice.size(); i++)
            {
                if(!(comboFaces.contains(player.currentDice.get(i).rollValue)))
                {
                    posNotCombo.add(i);
                }
            }
            
            if("True".equals(System.getProperty("traceMode")) && comboFaces.size() > 0){
                String outputFaces = "";
                for (Faces face:comboFaces){
                    outputFaces += face.name() + " ";
                }
                LOG.trace( outputFaces + "are the combos.");
            }
            //run the random strategy which will take care of the re-rolling of the non-combo dices
            continueTurn =  runRandomStrategy(player, numRolls);
        }
        return continueTurn;
        
    }

    private void logReRollDice(List <Integer> pos)
    {
         //Log the specific dice that is getting rerolled
         String posOutput = "";
         for(Integer num: pos){
             posOutput += (num + 1) + " ";
         }
         LOG.trace("Dices " + posOutput + "are re-rolled");
    }
}
