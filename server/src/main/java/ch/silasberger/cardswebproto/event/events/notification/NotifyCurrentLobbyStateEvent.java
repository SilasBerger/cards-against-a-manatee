package ch.silasberger.cardswebproto.event.events.notification;

import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.model.InitialLobbyState;

public class NotifyCurrentLobbyStateEvent extends AbstractNotificationEvent {

    private InitialLobbyState initialLobbyState;

    public NotifyCurrentLobbyStateEvent() {
    }

    public NotifyCurrentLobbyStateEvent(InitialLobbyState initialLobbyState) {
        this.initialLobbyState = initialLobbyState;
    }

    public InitialLobbyState getInitialLobbyState() {
        return initialLobbyState;
    }

    public void setInitialLobbyState(InitialLobbyState initialLobbyState) {
        this.initialLobbyState = initialLobbyState;
    }

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
