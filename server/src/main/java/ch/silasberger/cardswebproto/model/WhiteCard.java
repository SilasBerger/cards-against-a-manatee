package ch.silasberger.cardswebproto.model;

public class WhiteCard {

    public WhiteCard(int id, String value) {
        this.id = id;
        this.value = value;
    }

    private final int id;
    private final String value;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
