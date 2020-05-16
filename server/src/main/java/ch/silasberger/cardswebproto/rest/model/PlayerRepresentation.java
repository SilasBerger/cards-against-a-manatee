package ch.silasberger.cardswebproto.rest.model;

import ch.silasberger.cardswebproto.remoteplayer.RemotePlayer;

import java.util.UUID;

public class PlayerRepresentation {
    public UUID id;
    public String name;

    public PlayerRepresentation(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public static PlayerRepresentation from(RemotePlayer player) {
        return new PlayerRepresentation(player.getId(), player.getName());
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
