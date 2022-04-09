package ch.silasberger.cardswebproto.model;

public class BlackCard {

    public BlackCard(int id, String value, int pick) {
        this.id = id;
        this.value = value;
        this.pick = pick;
    }

    private final int id;
    private final String value;
    private final int pick;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public int getPick() {
        return pick;
    }
}
