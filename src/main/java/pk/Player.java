package pk;

import java.util.Random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Player {
    String name;
    int gamesWon;
    int score;
    int numSkulls;
    List <Dice> myDice = new ArrayList<>(); 
    List <Dice> currentDice = new ArrayList<>(); 
    List <Dice> skullDice = new ArrayList<>();

    public Player(String name){
        this.name = name;
        gamesWon = 0;
        score = 0;
        numSkulls = 0;
    }

    public String getName(){
        return name;
    }


    public void turn(){
        numSkulls = 0;
        myDice.clear();
        currentDice.clear();
        skullDice.clear();
        System.out.println(name + " is rolling");

        //Initial Roll
        for (int j = 0; j < 8; j++)
        {
            myDice.add(new Dice());
            System.out.println(myDice.get(j).getRollValue());
        }
        currentDice = myDice;

        //Rerolls
        int numRolls = 1;
        while(true)
        {
            //check if player can continue rolling or not
            if (continueTurn())
            {
                int numActiveDice = 8 - numSkulls;

                //Reroll random number of dice that are not skulls
                Random randomNumber = new Random();
                int numReRoll = randomNumber.nextInt(numActiveDice);

                //If numReRoll is 0, no dice are reroll, so we end turn
                //If numReRoll is not 0, we add 1 because we must roll atleast 2 dice at a time
                sortPlayerDice();
                if (numReRoll != 0)
                {
                    numReRoll++;
                    List <Dice> rerolledDice = new ArrayList<>(); 
                    
                    //randomly reroll the dice
                    for(int k = 0; k < numReRoll; k++)
                    {
                        int pos = randomNumber.nextInt(numActiveDice--);
                        rerolledDice.add(currentDice.get(pos));
                        rerolledDice.get(k).roll();
                        currentDice.remove(pos);
                    }
                    currentDice.addAll(rerolledDice);

                    numRolls++;
                    System.out.println("\n-Roll " + numRolls + "-");
                    printDice();

                }
                else{
                    System.out.println("\nPlayer decided not to re-roll");
                    break;
                }
            }
            else{
                break;
            }
        }
        
        System.out.println("\n-Final Values of the Dice-");
        printDice();
    }
    private Boolean continueTurn(){
        //Check if turn ends. Turn ends when there are 3 or more skulls in player dice set
        //return Boolean: true if turn is not over, false if turn is over

        //Counts the number of skulls rolled and remove the skulls from the currentDice
        Iterator iter = currentDice.iterator();

        while(iter.hasNext())
        {
            Dice die = (Dice)iter.next();
            if (die.getRollValue().equals(Faces.SKULL)){
                numSkulls++;
                skullDice.add(die);
                iter.remove();
            }
        }

        if (numSkulls >= 3)
        {
            System.out.println("\nTurn is over! More than 3 skulls were collected.");
            return false;
        }
        else
        {
            return true;
        }
    }

    private void printDice(){
        //Prints the 8 Dice information 
        sortPlayerDice();

        for (int m = 0; m < currentDice.size(); m++)
        {
            System.out.println( (m+1) + " " + currentDice.get(m).getRollValue());
        }
        for (Dice die:skullDice)
        {
            System.out.println("- " + die.getRollValue());
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
}
