import pk.Dice;
import pk.Faces;
import pk.Player;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        Player player1 = new Player("player1");

        for (int i=0; i < 42; i++){
            System.out.println("\n=======GAME " + (i+1) + "=======");

            player1.turn();
        }

        System.out.println("\nThat's all folks!");
    }
    
}
