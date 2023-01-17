package pk;

import java.util.ArrayList;
import org.apache.logging.log4j.*;

public class Simulation {
    private static Logger LOG = LogManager.getLogger(Simulation.class);
    int totGames;
    int totPlayers;

    public Simulation(int totGames, int totPlayers){
        this.totGames = totGames;
        //Must atleast have two players playing
        if (totPlayers >=2){
            this.totPlayers = totPlayers;
        }
        else{
            this.totPlayers = 2;
        }
    }

    public void runSimulation()
    {
        //Create the players
        ArrayList <Player> players = new ArrayList<>();
        for (int i = 0; i < totPlayers; i++)
        {
            players.add(new Player("player" + (i+1)));
        }

        //Run the games
        for (int i=0; i < totGames; i++){
            new Game((i+1), players).runGame();
        }

        //Print each player's winnings to console
        System.out.println("=======Final Simulation Outcome=======");
        double wins;
        for(Player player:players)
        {
            wins = (double)player.getNumWins()/42*100;
            System.out.printf("%s wins: %2.2f%%\n",player.getName() , wins);
        }

        //Kepping log messages seperate so that it doesn't split the console output from above
        LOG.trace("Final Simulation Outcome");
        for(Player player:players)
        {
            wins = (double)player.getNumWins()/42*100;
            LOG.trace(String.format("%s wins: %2.2f%%",player.getName() , wins));
        }
    }
}
