package pk;
import java.util.Random;

public class Dice {
    Faces rollValue;

    public Dice(){
        roll();
    }

    public Dice(Faces face){
        rollValue = face;
    }

    public Faces roll() {
        int howManyFaces = Faces.values().length;
        Random bag = new Random();
        rollValue = Faces.values()[bag.nextInt(howManyFaces)];
        return rollValue;
    }

    public Faces getRollValue(){
        return rollValue;
    }
    
}
