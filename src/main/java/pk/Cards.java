package pk;

public enum Cards {
    SEABATTLE2(2,300, "Sea Battle 2"),
    SEABATTLE3(3,500, "Sea Battle 3"),
    SEABATTLE5(5,1000, "Sea Battle 5"),
    MONKEYBUSINESS(0,0,"Moneky Business"),
    GOLD(0,100, "Gold"),
    DIAMOND(0,100, "Diamond"),
    NOP(0,0, "Empty Card");

    public final int num; //represents a number depending on the card
    public final int points; //represent the points for the card
    public final String cardName;

    private Cards(int num, int points, String name){
        this.num = num;
        this.points = points;
        this.cardName = name;
    }
}
