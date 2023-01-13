import pk.Dice;
import pk.Faces;
import pk.Player;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        for (int i=0; i < 42; i++){
            System.out.println("\n=======GAME " + (i+1) + "=======");

            player1.turn();
            player2.turn();

            //Determine winner of the game
            if (player1.getScore() >= 6000 && player2.getScore() >= 6000)
            {
                if (player1.getScore() > player2.getScore())
                {
                    player1.increaseNumWins();
                }
                else if (player2.getScore() > player1.getScore()){
                    player2.increaseNumWins();
                }
                else{
                    player1.increaseNumWins();
                    player2.increaseNumWins();
                }
            }
            else if (player1.getScore() >= 6000){
                player1.increaseNumWins();
            }
            else if (player2.getScore() >= 6000){
                player2.increaseNumWins();
            }
        }

        double playerOneWins = player1.getNumWins()/42;
        double playerTwoWins = player2.getNumWins()/42;

        System.out.println("=======Final Simulation Outcome=======");
        System.out.printf("Player 1 wins: %2.2f%%\n", playerOneWins);
        System.out.printf("Player 2 wins: %2.2f%%\n", playerTwoWins);

        System.out.println("\nThat's all folks!");
    }
    
}
