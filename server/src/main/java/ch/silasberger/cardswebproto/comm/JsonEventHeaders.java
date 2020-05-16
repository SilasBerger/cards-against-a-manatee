package ch.silasberger.cardswebproto.comm;

public class JsonEventHeaders {

    private String eventName;
    private long iat;

    public JsonEventHeaders() {
    }

    public JsonEventHeaders(String eventName, long iat) {
        this.eventName = eventName;
        this.iat = iat;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public long getIat() {
        return iat;
    }

    public void setIat(long iat) {
        this.iat = iat;
    }
}
