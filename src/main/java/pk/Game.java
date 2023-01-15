package pk;

public class Game {
    int gameNumber;
    Player player1;
    Player player2;

    public Game(int gameNumer, Player player1, Player player2){
        this.gameNumber = gameNumer;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void runGame(){
        //runs the game
        System.out.println("\n=======GAME " + gameNumber + "=======");
        player1.resetScore();
        player2.resetScore();

        while (true){
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
                break;
            }
            else if (player1.getScore() >= 6000){
                player1.increaseNumWins();
                break;
            }
            else if (player2.getScore() >= 6000){
                player2.increaseNumWins();
                break;
            }
        }
    }
    
}
