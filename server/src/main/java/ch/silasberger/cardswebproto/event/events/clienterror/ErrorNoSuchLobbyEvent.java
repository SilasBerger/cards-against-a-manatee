package ch.silasberger.cardswebproto.event.events.clienterror;

import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.model.NonceId;

public class ErrorNoSuchLobbyEvent extends AbstractClientErrorEvent {

    private NonceId lobbyId;

    public ErrorNoSuchLobbyEvent() {
    }

    public ErrorNoSuchLobbyEvent(NonceId lobbyId) {
        this.lobbyId = lobbyId;
    }

    public NonceId getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(NonceId lobbyId) {
        this.lobbyId = lobbyId;
    }

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
