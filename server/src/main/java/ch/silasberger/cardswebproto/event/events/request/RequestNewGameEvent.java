package ch.silasberger.cardswebproto.event.events.request;

import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.util.ApplicationException;

public class RequestNewGameEvent extends AbstractRequestEvent {

    public RequestNewGameEvent() {
    }

    @Override
    public void executeOn(EventHandler handler) throws ApplicationException {
        handler.handle(this);
    }
}
