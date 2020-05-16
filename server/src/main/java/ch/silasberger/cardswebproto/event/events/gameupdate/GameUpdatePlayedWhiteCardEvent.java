package ch.silasberger.cardswebproto.event.events.gameupdate;

import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.model.WhiteCardRepresentation;

import java.util.UUID;

public class GameUpdatePlayedWhiteCardEvent extends AbstractGameUpdateEvent {

    private WhiteCardRepresentation card;
    private UUID playerId;

    public GameUpdatePlayedWhiteCardEvent() {
    }

    public GameUpdatePlayedWhiteCardEvent(WhiteCardRepresentation card, UUID playerId) {
        this.card = card;
        this.playerId = playerId;
    }

    public WhiteCardRepresentation getCard() {
        return card;
    }

    public void setCard(WhiteCardRepresentation card) {
        this.card = card;
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
