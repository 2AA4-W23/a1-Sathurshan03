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
    ArrayList<Integer> posNotCombo;
    private static Logger LOG = LogManager.getLogger(Simulation.class);
    
    public Strategy(strategies currentStrategy ){
        this.currentStrategy = currentStrategy;
    }

    public void changeStrategy(strategies newStrategy){
        currentStrategy = newStrategy;
    }

    public boolean useStrategy(DiceCup diceCup, int numRolls)
    {
        //Run the logic of the player's strategy
        if (currentStrategy.equals(strategies.RANDOM)){
            return runRandomStrategy(diceCup, numRolls);
        }
        else if (currentStrategy.equals(strategies.COMBO)){
            return runComboStrategy(diceCup, numRolls);
        }
        return false;
    }
    
    private boolean runRandomStrategy(DiceCup diceCup, int numRolls)
    {
        //The logic of re-rolling for the RANDOM strategy

        int numActiveDice;
        if (currentStrategy.equals(strategies.COMBO))
        {
            //if the strategy is combo, then set the size to the # of dices that are not combos
            numActiveDice = posNotCombo.size();
        }
        else{
            numActiveDice = diceCup.getCurrentDiceSize();
        }

        //Reroll random number of dice that are not skulls
        Random randomNumber = new Random();
        int numReRoll = randomNumber.nextInt(numActiveDice + 1);

        //If numReRoll is 0 or 1, no dice are reroll, so we end turn
        if (numReRoll != 0 && numReRoll != 1 )
        {
            ArrayList <Integer> pos = new ArrayList<>(); 
            
            //randomly find the positions of dice to re-roll
            for(int k = 0; k < numReRoll; )
            {
                int index;
                if (currentStrategy.equals(strategies.COMBO))
                {
                    index = randomNumber.nextInt(diceCup.getCurrentDiceSize());
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
            diceCup.reRoll(pos);

            //Log the results
            if("True".equals(System.getProperty("traceMode"))){
                diceCup.logReRollDice(pos);
                LOG.trace("-Roll " + numRolls + "-");
                diceCup.logDice();
            }
            
            return true;
        }
        else{
            return false;
        }
    }

    private boolean runComboStrategy(DiceCup diceCup, int numRolls)
    {
        //The logic of re-rolling for the COMBO strategy
        //The goal is to only re-roll the dice that do not already have a combo

        //Find the number of dice that are of each face
        int [] numFace = diceCup.rolledFacesInfo();

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
            continueTurn = runRandomStrategy(diceCup, numRolls);
            currentStrategy = strategies.COMBO;

        }
        else{
            //Find the positions of dices that are not combo dices
            posNotCombo = diceCup.findDiceNotCombo(comboFaces);

            //Do not re-roll if all active dices are part of a combo
            if(posNotCombo.size() == 0)
            {
                return false;
            }
            
            if("True".equals(System.getProperty("traceMode")) && comboFaces.size() > 0){
                String outputFaces = "";
                for (Faces face:comboFaces){
                    outputFaces += face.name() + " ";
                }
                LOG.trace( outputFaces + "are the combos.");
            }
            //run the random strategy which will take care of the re-rolling of the non-combo dices
            continueTurn =  runRandomStrategy(diceCup, numRolls);
        }
        return continueTurn;
        
    }


}
