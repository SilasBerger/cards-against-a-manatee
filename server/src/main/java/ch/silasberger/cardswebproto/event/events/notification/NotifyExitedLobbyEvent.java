package ch.silasberger.cardswebproto.event.events.notification;

import ch.silasberger.cardswebproto.event.EventHandler;

public class NotifyExitedLobbyEvent extends AbstractNotificationEvent {
    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
