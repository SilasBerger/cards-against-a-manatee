package ch.silasberger.cardswebproto.event;

public class EventHeaders {

    private final long iat;
    private String sourceChannel;

    public EventHeaders(long iat) {
        this.iat = iat;
    }

    public long getIat() {
        return iat;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }
}
