package ch.silasberger.cardswebproto.event.events.notification;

import ch.silasberger.cardswebproto.event.EventHandler;

import java.util.UUID;

public class NotifyPlayerLeftLobbyEvent extends AbstractNotificationEvent {

    private UUID playerId;

    public NotifyPlayerLeftLobbyEvent() {
    }

    public NotifyPlayerLeftLobbyEvent(UUID playerId) {
        this.playerId = playerId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
