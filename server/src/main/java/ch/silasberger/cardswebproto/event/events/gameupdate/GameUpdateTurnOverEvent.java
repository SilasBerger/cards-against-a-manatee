package ch.silasberger.cardswebproto.event.events.gameupdate;

import ch.silasberger.cardswebproto.event.EventHandler;

public class GameUpdateTurnOverEvent extends AbstractGameUpdateEvent {
    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
