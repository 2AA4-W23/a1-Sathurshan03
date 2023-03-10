package pk;

import java.util.List;
import java.util.ArrayList;

public class Simulation {
    int totGames;
    int totPlayers;
    String [] playersStrategy;
    Log log = new Log();

    public Simulation(int totGames, String [] playerStrategy){
        this.totGames = totGames;
        this.playersStrategy = playerStrategy;
        this.totPlayers = playerStrategy.length;
    }

    public void runSimulation()
    {
        //Create the players
        List <Player> players = new ArrayList<>();
        for (int i = 0; i < totPlayers; i++)
        {
            players.add(new Player("player" + (i+1), playersStrategy[i]));
        }

        //Run the games
        for (int i=0; i < totGames; i++){
            new Game((i+1), players).runGame();
        }

        //log each player's winnings
        double wins;
        log.logMessage("Final Simulation Outcome");
        for(Player player:players)
        {
            wins = (double)player.getNumWins()/totGames*100;
            log.logMessage(String.format("%s wins: %2.2f%%",player.getName() , wins));
        }

        //Print each player's winnings to console
        System.out.println("\n=======Final Simulation Outcome=======");
        for(Player player:players)
        {
            wins = (double)player.getNumWins()/totGames*100;
            System.out.printf("%s wins: %2.2f%%\n",player.getName() , wins);
        }
    }
}
