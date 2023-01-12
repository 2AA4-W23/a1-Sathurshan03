import pk.Dice;
import pk.Faces;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        for (int i=0; i < 42; i++){
            System.out.println("\nGAME " + (i+1));
            System.out.println("I'm rolling a dice");
            Dice myDice [] = new Dice [8]; 
            for (int j = 0; j < 8; j++)
            {
                myDice[j] = new Dice();
                System.out.println(myDice[j].getRollValue());
            }

            //Check if turn ends
            int numSkulls = 0;
            for (int j = 0; j < 8; j++)
            {
                if (myDice[j].getRollValue().equals(Faces.SKULL)){
                    numSkulls++;
                }
            }

            if (numSkulls >= 3)
            {
                System.out.println("Turn is over!");
            }
        }

        System.out.println("\nThat's all folks!");
    }
    
}
