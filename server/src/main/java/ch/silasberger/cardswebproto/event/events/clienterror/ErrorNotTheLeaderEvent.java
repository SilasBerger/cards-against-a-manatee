package ch.silasberger.cardswebproto.event.events.clienterror;

import ch.silasberger.cardswebproto.event.EventHandler;

public class ErrorNotTheLeaderEvent extends AbstractClientErrorEvent {
    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
