package ch.silasberger.cardswebproto.event.events.request;

import ch.silasberger.cardswebproto.event.EventHandler;

public class RequestExitLobbyEvent extends AbstractRequestEvent {
    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
