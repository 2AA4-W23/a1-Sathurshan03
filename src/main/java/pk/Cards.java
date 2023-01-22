package pk;

public enum Cards {
    SEABATTLE2(2,300),
    SEABATTLE3(3,500),
    SEABATTLE5(5,1000),
    NOP(0,0);

    public final int num; //represents a number depending on the card
    public final int points; //represent the points for the card

    private Cards(int num, int points){
        this.num = num;
        this.points = points;
    }
}
