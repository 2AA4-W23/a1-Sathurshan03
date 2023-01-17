package pk;

import java.util.ArrayList;
import org.apache.logging.log4j.*;

public class Game {
    private static Logger LOG = LogManager.getLogger(Simulation.class);
    int gameNumber;
    ArrayList<Player> players;

    public Game(int gameNumer, ArrayList<Player> players){
        this.gameNumber = gameNumer;
        this.players = players;
    }

    public void runGame(){
        //runs the game
        LOG.trace("=======GAME " + gameNumber + "=======");

        //Reset each player's score
        for (Player player:players)
        {
            player.resetScore();
        }

        //Take turns rolling the dice
        boolean winnerFlag = false;
        while (true){
            for (Player player:players)
            {
                player.turn();
                if(player.getScore()>= 6000)
                {
                    //give every other play one more turn and then determine the winner
                    oneMoreRound(player);
                    if(isWinner()){
                        winnerFlag = true;
                        break;
                    }
                }
            }
            if (winnerFlag){
                //winner of the game has been determines
                break;
            }
        }
    }
    public void oneMoreRound(Player player)
    {
        //Player scored over 6000, by rulebook, every other player has one more turn before final scores are evaluated

        int playerPos = players.indexOf(player);

        //Every other player gets one more turn 
        //Players turn after player in list
        for (int i = playerPos+1; i<players.size(); i++)
        {
            players.get(i).turn();
        }

        //Players turn before player in list
        for (int i = 0; i < playerPos; i++){
            players.get(i).turn();
        }
    }

    public boolean isWinner()
    {
        //Determine if there is a winner of the game (highest score above 6000)
        int maxValue = 0;
        Player maxScorePlayer = players.get(0);

        //Find the max score
        for (Player player:players){
            if (player.getScore() > maxValue)
            {
                maxValue = player.getScore();
                maxScorePlayer = player;
            }
        }

        //Check if max score is above 6000
        if (maxValue >= 6000){
            maxScorePlayer.increaseNumWins();
            return true;
        }
        return false;
    }
    
}
