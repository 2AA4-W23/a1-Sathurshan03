package pk;

import java.util.ArrayList;
import org.apache.logging.log4j.*;

public class Simulation {
    private static Logger LOG = LogManager.getLogger(Simulation.class);
    int totGames;
    int totPlayers;
    String [] playersStrategy;

    public Simulation(int totGames, String [] playerStrategy){
        this.totGames = totGames;
        this.playersStrategy = playerStrategy;
        this.totPlayers = playerStrategy.length;
    }

    public void runSimulation()
    {
        //Create the players
        ArrayList <Player> players = new ArrayList<>();
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
        if("True".equals(System.getProperty("traceMode"))){
            LOG.trace("Final Simulation Outcome");
            for(Player player:players)
            {
                wins = (double)player.getNumWins()/totGames*100;
                LOG.trace(String.format("%s wins: %2.2f%%",player.getName() , wins));
            }
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
