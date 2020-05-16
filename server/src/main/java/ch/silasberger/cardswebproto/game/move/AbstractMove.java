package ch.silasberger.cardswebproto.game.move;

import ch.silasberger.cardswebproto.lobby.LobbyMember;

public class AbstractMove {

    private final LobbyMember player;

    public AbstractMove(LobbyMember player) {
        this.player = player;
    }

    public LobbyMember getPlayer() {
        return player;
    }
}
