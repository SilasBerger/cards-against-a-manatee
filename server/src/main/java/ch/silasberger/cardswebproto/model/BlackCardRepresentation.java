package ch.silasberger.cardswebproto.model;

public class BlackCardRepresentation {

    private int id;
    private String text;
    private int pick;

    public BlackCardRepresentation() {
    }

    public BlackCardRepresentation(int id, String text, int pick) {
        this.id = id;
        this.text = text;
        this.pick = pick;
    }

    public static BlackCardRepresentation from(BlackCard blackCard) {
        return new BlackCardRepresentation(blackCard.getId(), blackCard.getValue(), blackCard.getPick());
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

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }
}
