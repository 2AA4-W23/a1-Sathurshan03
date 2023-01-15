import java.util.ArrayList;

import pk.Game;
import pk.Player;

public class PiratenKarpen {

    public static void main(String[] args) {
        int totGames = 42;
        int totPlayers = 2;
        ArrayList <Player> players = new ArrayList<>();
        System.out.println("Welcome to Piraten Karpen Simulator!");

        //Create the players
        for (int i = 0; i < totPlayers; i++)
        {
            players.add(new Player("player" + (i+1)));
        }

        //Run the games
        for (int i=0; i < totGames; i++){
            new Game((i+1), players).runGame();
        }

        //Calculate and display the winnings data
        System.out.println("=======Final Simulation Outcome=======");
        double wins;
        for(Player player:players)
        {
            wins = (double)player.getNumWins()/42*100;
            System.out.printf("%s wins: %2.2f%%\n",player.getName() , wins);
        }

        System.out.println("\nThat's all folks!");
    }
    
}
