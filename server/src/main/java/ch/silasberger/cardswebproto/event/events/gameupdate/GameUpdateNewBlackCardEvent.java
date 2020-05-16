package ch.silasberger.cardswebproto.event.events.gameupdate;

import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.model.BlackCardRepresentation;

public class GameUpdateNewBlackCardEvent extends AbstractGameUpdateEvent {

    private BlackCardRepresentation card;

    public GameUpdateNewBlackCardEvent() {
    }

    public GameUpdateNewBlackCardEvent(BlackCardRepresentation card) {
        this.card = card;
    }

    public BlackCardRepresentation getCard() {
        return card;
    }

    public void setCard(BlackCardRepresentation card) {
        this.card = card;
    }

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
