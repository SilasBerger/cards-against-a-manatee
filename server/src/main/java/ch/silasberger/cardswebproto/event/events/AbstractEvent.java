package ch.silasberger.cardswebproto.event.events;

import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.event.EventHeaders;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractEvent {

    @JsonIgnore
    private EventHeaders headers;

    public AbstractEvent() {
        this.headers = new EventHeaders(System.currentTimeMillis());
    }

    public AbstractEvent(EventHeaders headers) {
        this.headers = headers;
    }

    public EventHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(EventHeaders headers) {
        this.headers = headers;
    }

    public void setIat(long iat) {
        if (headers == null) {
            headers = new EventHeaders(iat);
        }
    }

    public abstract void executeOn(EventHandler handler);
}
