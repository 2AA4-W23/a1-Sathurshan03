import pk.Dice;
import pk.Faces;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");
        Dice myDice [] = new Dice [8]; 
        for (int i = 0; i < 8; i++)
        {
            myDice[i] = new Dice();
            System.out.println(myDice[i].getRollValue());
        }

        //Check if turn ends
        int numSkulls = 0;
        for (int i = 0; i < 8; i++)
        {
            if (myDice[i].getRollValue().equals(Faces.SKULL)){
                numSkulls++;
            }
        }

        if (numSkulls >= 3)
        {
            System.out.println("Turn is over!");
        }

        System.out.println("That's all folks!");
    }
    
}
