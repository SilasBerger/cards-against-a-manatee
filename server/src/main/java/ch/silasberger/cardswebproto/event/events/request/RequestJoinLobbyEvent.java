package ch.silasberger.cardswebproto.event.events.request;

import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.model.NonceId;
import com.fasterxml.jackson.annotation.JsonSetter;

public class RequestJoinLobbyEvent extends AbstractRequestEvent {

    private NonceId lobbyId;

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }

    public NonceId getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(NonceId lobbyId) {
        this.lobbyId = lobbyId;
    }

    @JsonSetter("lobbyId")
    public void setLobbyIdFromString(String nonceIdFromString) {
        this.lobbyId = NonceId.fromString(nonceIdFromString);
    }
}
