package ch.silasberger.cardswebproto.model;

public class WhiteCardRepresentation {

    private int id;
    private String text;

    public WhiteCardRepresentation() {
    }

    public WhiteCardRepresentation(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public static WhiteCardRepresentation from(WhiteCard whiteCard) {
        return new WhiteCardRepresentation(whiteCard.getId(), whiteCard.getValue());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
