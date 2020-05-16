package ch.silasberger.cardswebproto.rest.model;

import ch.silasberger.cardswebproto.lobby.Lobby;
import ch.silasberger.cardswebproto.model.NonceId;

public class LobbyRepresentation {

    public final String id;

    public LobbyRepresentation(NonceId id) {
        this.id = id.value;
    }

    public static LobbyRepresentation from(Lobby lobby) {
        return new LobbyRepresentation(lobby.getId());
    }
}
