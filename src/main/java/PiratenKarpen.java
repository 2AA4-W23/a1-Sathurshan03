import pk.Game;
import pk.Player;

public class PiratenKarpen {

    public static void main(String[] args) {
        int totGames = 42;
        System.out.println("Welcome to Piraten Karpen Simulator!");

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        for (int i=0; i < totGames; i++){
            new Game((i+1), player1, player2).runGame();
        }

        double playerOneWins = (double)player1.getNumWins()/42*100;
        double playerTwoWins = (double)player2.getNumWins()/42*100;

        System.out.println("=======Final Simulation Outcome=======");
        System.out.printf("Player 1 wins: %2.2f%%\n", playerOneWins);
        System.out.printf("Player 2 wins: %2.2f%%\n", playerTwoWins);

        System.out.println("\nThat's all folks!");
    }
    
}
