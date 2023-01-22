package pk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

enum strategies {
    RANDOM, COMBO, SABERCOMBO
}

public class Strategy {

    strategies currentStrategy;
    ArrayList<Integer> posNotCombo;
    Log log = new Log();
    Cards currentCard;
    
    public Strategy(strategies currentStrategy ){
        this.currentStrategy = currentStrategy;
    }


    public void setRoundCard(Cards card){
        currentCard = card;
    }

    public boolean useStrategy(DiceCup diceCup, int numRolls)
    {
        //Run the logic of the player's strategy
        if (currentCard.equals(Cards.SEABATTLE2) || currentCard.equals(Cards.SEABATTLE3) || currentCard.equals(Cards.SEABATTLE5)){
            return runSeaBattleCombo(diceCup, numRolls);
        }
        else if (currentStrategy.equals(strategies.RANDOM)){
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
        if (currentStrategy.equals(strategies.COMBO) || currentStrategy.equals(strategies.SABERCOMBO))
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
                if (currentStrategy.equals(strategies.COMBO)|| currentStrategy.equals(strategies.SABERCOMBO))
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
            diceCup.logReRollDice(pos);
            log.logMessage("-Roll " + numRolls + "-");
            diceCup.logDice();
            
            
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
            
            //Log the combos
            String outputFaces = "";
            for (Faces face:comboFaces){
                outputFaces += face.name() + " ";
            }
            log.logMessage(outputFaces + "are the combos.");

            //run the random strategy which will take care of the re-rolling of the non-combo dices
            continueTurn =  runRandomStrategy(diceCup, numRolls);
        }
        return continueTurn;
    }
    public boolean runSeaBattleCombo(DiceCup diceCup, int numRolls){
        //When the player has a seabattle, they must get a certain amount of sabers to earn points
        //returns true if the player has to re-roll
        log.logMessage(Faces.SABER.name() + " is the combo");

        //find the positions that are not Sabers
        ArrayList<Faces> saberFace = new ArrayList<>();
        saberFace.add(Faces.SABER);
        posNotCombo = diceCup.findDiceNotCombo(saberFace);

        int numSabers = diceCup.countNumFace(Faces.SABER);

        //Do not re-roll if the minimum number of sabers has been reached
        Boolean continueTurn;
        if (numSabers >= currentCard.num)
        {
            continueTurn = false;
        }
        else
        {
            //Run random strategy on the dices that do not have the face Saber
            strategies playStrategies = currentStrategy;
            currentStrategy = strategies.SABERCOMBO;
            continueTurn = runRandomStrategy(diceCup, numRolls);
            currentStrategy = playStrategies;
        }

        return continueTurn;
    }


}
