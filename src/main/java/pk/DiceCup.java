package pk;

import java.util.*;

public class DiceCup {
    //A collection of Dice
    
    ArrayList <Dice> myDice; 
    List <Dice> currentDice; 
    List <Dice> skullDice;
    Log log = new Log();

    public DiceCup(){
        myDice = new ArrayList<>();
        currentDice = new ArrayList<>();
        skullDice = new ArrayList<>();
    }

    public int getCurrentDiceSize(){
        return currentDice.size();
    }

    public void intialRoll()
    {
        //Initial Roll
        for (int j = 0; j < 8; j++)
        {
            myDice.add(new Dice());
        }
        currentDice = myDice;

        logDice();
    }

    public void reRoll(ArrayList<Integer> pos)
    {
         //Re-roll the selected pos dice 
         for (Integer index: pos){
            currentDice.get(index).roll();
        }
    }

    public Boolean underThreeSkull(){
        //Checks the number of skulls. Turn ends when there are 3 or more skulls in diceCup set
        //return Boolean: true if turn there are three of more skulls in the diceCip 

        //Counts the number of skulls rolled and remove the skulls from the currentDice
        Iterator<Dice> iter = currentDice.iterator();
        while(iter.hasNext())
        {
            Dice die = (Dice)iter.next();
            if (die.getRollValue().equals(Faces.SKULL)){
                skullDice.add(die);
                iter.remove();
            }
        }

        //check if turn is over by examining the number of skulls
        if (skullDice.size() >= 3)
        {
            log.logMessage("Turn is over! More than 3 skulls were collected.");
            return false;
        }
        else
        {
            return true;
        }
    }

    public int[] rolledFacesInfo()
    {
        //Find the number of dice that are of each face
        int numFace[] = new int[5]; //5 non-skull faces
        for(Dice activeDice:currentDice)
        {
            int index = activeDice.rollValue.ordinal();
            numFace[index] ++;
        }

        return numFace;
    }

    public ArrayList<Integer> findDiceNotCombo(ArrayList<Faces> comboFaces)
    {
         //Find the positions of dices that are not combo dices
         ArrayList<Integer> posNotCombo = new ArrayList<>();
         for (int i = 0; i<getCurrentDiceSize(); i++)
         {
             if(!(comboFaces.contains(currentDice.get(i).rollValue)))
             {
                 posNotCombo.add(i);
             }
         }

         return posNotCombo;
    }

    public void sortPlayerDice()
    {
        //Sorts the player dice based on the enum order
        Collections.sort(currentDice, new Comparator<Dice>() {
            @Override
            public int compare(Dice die1, Dice die2) {
                return die1.getRollValue().compareTo(die2.getRollValue());
            }});
    }

    public void logFinalDice(){
            log.logMessage("-Final Values of the Dice-");
            logDice();
    }

    public void logDice(){
        //logs the 8 Dice information 
        sortPlayerDice();

        for (int m = 0; m < currentDice.size(); m++)
        {
            log.logMessage((m+1) + " " + currentDice.get(m).getRollValue());
        }
        for (Dice die:skullDice)
        {
            log.logMessage("- " + die.getRollValue());
        }
    }

    public void logReRollDice(List <Integer> pos)
    {
        //Log the specific dice that is getting rerolled
        String posOutput = "";
        for(Integer num: pos){
            posOutput += (num + 1) + " ";
        }
        log.logMessage("Dices " + posOutput + "are re-rolled");
    }

    public int calculateDiceCupScore()
    {
        int turnScore = 0;

        //Calculate the player's score at the end of their turn
        if (skullDice.size() >= 3)
        {
            turnScore += 0;
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
                    turnScore += 100;
                }
                else if (counter == 4)
                {
                    turnScore += 200;
                }
                else if (counter == 5)
                {
                    turnScore += 500;
                }
                else if (counter == 6)
                {
                    turnScore += 1000;
                }
                else if (counter == 7)
                {
                    turnScore += 2000;
                }
                else if (counter >= 8)
                {
                    turnScore += 4000;
                }

                //Reward points for rolling diamond and gold coins
                if (Faces.values()[i].equals(Faces.DIAMOND) || Faces.values()[i].equals(Faces.GOLD))
                {
                    turnScore += counter * 100;
                }
            }
        }

        return turnScore;
    }
    
}
