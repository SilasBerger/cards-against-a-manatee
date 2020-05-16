package ch.silasberger.cardswebproto.event.events.gamerequest;

import ch.silasberger.cardswebproto.event.EventHandler;

public class GameRequestNextTurnEvent extends AbstractGameRequestEvent {
    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
