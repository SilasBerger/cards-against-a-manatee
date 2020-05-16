package ch.silasberger.cardswebproto.event.events.request;

import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.remoteplayer.RemotePlayer;

public abstract class AbstractRequestEvent extends AbstractEvent {

    private RemotePlayer player;

    public RemotePlayer getPlayer() {
        return player;
    }

    public void setPlayer(RemotePlayer player) {
        this.player = player;
    }
}
