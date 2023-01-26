package pk;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class DiceCup {
    //A collection of Dice
    
    List <Dice> myDice; 
    List <Dice> currentDice; 
    List <Dice> skullDice;
    Log log = new Log();
    Cards card;

    public DiceCup(Cards card){
        myDice = new ArrayList<>();
        currentDice = new ArrayList<>();
        skullDice = new ArrayList<>();
        this.card = card;
    }

    public int getCurrentDiceSize(){
        return currentDice.size();
    }

    public void initialRoll()
    {
        //Initial Roll
        for (int j = 0; j < 8; j++)
        {
            myDice.add(new Dice());
        }
        currentDice = myDice;

        logDice();
    }

    public void reRoll(List<Integer> pos)
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

    public List<Integer> findDiceNotCombo(List<Faces> comboFaces)
    {
         //Find the positions of dices that are not combo dices
         List<Integer> posNotCombo = new ArrayList<>();
         for (int i = 0; i<getCurrentDiceSize(); i++)
         {
             if(!(comboFaces.contains(currentDice.get(i).rollValue)))
             {
                 posNotCombo.add(i);
             }
         }

         return posNotCombo;
    }

    public int countNumFace(Faces face)
    {
        //counts the number of the dice that has the face rolled
        int counter = 0;
        for(Dice die:currentDice)
        {
            if(die.rollValue.equals(face)){
                counter++;
            }
        }

        return counter;
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
        if (skullDice.size() < 3 )
        {
            int total;

            //Diamond face count
            int numDiamonds = card.equals(Cards.DIAMOND) ? countNumFace(Faces.DIAMOND) + 1 : countNumFace(Faces.DIAMOND);
            //Gold face count
            int numGold = card.equals(Cards.GOLD) ? countNumFace(Faces.GOLD) + 1 : countNumFace(Faces.GOLD);

            //MonkeyBusiness Card
            if (card.equals(Cards.MONKEYBUSINESS))
            {
                //count the numnber of monkey and parrot cards
                total = countNumFace(Faces.MONKEY) + countNumFace(Faces.PARROT);
                turnScore += getComboScore(total);
                
                
                //check if the other faces have combos
                turnScore += getComboScore(numDiamonds) + getComboScore(numGold) + getComboScore(countNumFace(Faces.SABER));
            }
            else
            {
                //Atleast three in a row combo

                
                
                turnScore += getComboScore(countNumFace(Faces.MONKEY)) + getComboScore(countNumFace(Faces.PARROT)) + getComboScore(numDiamonds) + getComboScore(numGold) + getComboScore(countNumFace(Faces.SABER));
            }

            //Reward points for rolling diamond and gold coins
            turnScore += (numDiamonds * 100 + numGold * 100);
        }

        //Sea of battle points
        if ((card.equals(Cards.SEABATTLE2)|| card.equals(Cards.SEABATTLE3)|| card.equals(Cards.SEABATTLE5)))
        {
            if (countNumFace(Faces.SABER) >= card.num )
            {
                //award points for a successful win in the sea of battles
                turnScore += card.points;
            }
            else{
                //no points for losing in the sea of battles
                turnScore = 0;
            }
        
        }

        return turnScore;
    }
    private int getComboScore(int combo)
    {
        //returns the combo score for the combo value
        if (combo == 3)
        {
            return 100;
        }
        else if (combo == 4)
        {
            return 200;
        }
        else if (combo == 5)
        {
            return 500;
        }
        else if (combo == 6)
        {
            return 1000;
        }
        else if (combo == 7)
        {
            return 2000;
        }
        else if (combo >= 8)
        {
            return 4000;
        }
        return 0;
    }
    
}
