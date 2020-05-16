package ch.silasberger.cardswebproto.event.events.notification;

import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.model.LobbyMemberRepresentation;

import java.util.UUID;

public class NotifyPlayerJoinedLobbyEvent extends AbstractNotificationEvent {

    private LobbyMemberRepresentation player;

    public NotifyPlayerJoinedLobbyEvent() {
    }

    public NotifyPlayerJoinedLobbyEvent(LobbyMemberRepresentation player) {
        this.player = player;
    }

    public LobbyMemberRepresentation getPlayer() {
        return player;
    }

    public void setPlayer(LobbyMemberRepresentation player) {
        this.player = player;
    }

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
