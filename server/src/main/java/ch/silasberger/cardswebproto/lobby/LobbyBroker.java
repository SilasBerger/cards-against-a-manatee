package ch.silasberger.cardswebproto.lobby;

import ch.silasberger.cardswebproto.model.NonceId;

import java.util.HashMap;
import java.util.Map;

public class LobbyBroker {

    private static LobbyBroker instance;

    private Map<NonceId, Lobby> lobbyMap;

    private LobbyBroker() {
        lobbyMap = new HashMap<>();
    }

    public static LobbyBroker getInstance() {
        if (instance == null) {
            instance = new LobbyBroker();
        }
        return instance;
    }

    public boolean register(Lobby lobby) {
        if (lobby == null) {
            return false;
        }
        lobbyMap.put(lobby.getId(), lobby);
        return true;
    }

    public boolean unregister(Lobby lobby) {
        if (lobby == null) {
            return false;
        }
        return unregister(lobby.getId());
    }

    public boolean unregister(NonceId id) {
        Lobby removed = lobbyMap.remove(id);
        return removed != null;
    }

    public Lobby get(NonceId id) {
        return lobbyMap.get(id);
    }
}
