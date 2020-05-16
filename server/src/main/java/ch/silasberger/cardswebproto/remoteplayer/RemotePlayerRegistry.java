package ch.silasberger.cardswebproto.remoteplayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RemotePlayerRegistry {

    private static RemotePlayerRegistry instance;

    private Map<UUID, RemotePlayer> playerMap;

    private RemotePlayerRegistry() {
        this.playerMap = new HashMap<>();
    }

    public static RemotePlayerRegistry getInstance() {
        if (instance == null) {
            instance = new RemotePlayerRegistry();
        }
        return instance;
    }

    public boolean register(RemotePlayer remotePlayer) {
        if (remotePlayer == null) {
            return false;
        }
        playerMap.put(remotePlayer.getId(), remotePlayer);
        return true;
    }

    public boolean unregister(RemotePlayer remotePlayer) {
        if (remotePlayer == null) {
            return false;
        }
        RemotePlayer removed = playerMap.remove(remotePlayer.getId());
        return removed != null;
    }

    public boolean unregister(UUID remotePlayerId) {
        RemotePlayer removed = playerMap.remove(remotePlayerId);
        return removed != null;
    }

    public RemotePlayer get(UUID remotePlayerId) {
        return playerMap.get(remotePlayerId);
    }

    public boolean exists(UUID remotePlayerId) {
        return get(remotePlayerId) != null;
    }
}
