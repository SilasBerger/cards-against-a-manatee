package ch.silasberger.cardswebproto.event.events.move;

import ch.silasberger.cardswebproto.event.EventHandler;

public class MovePlayWhiteCardEvent extends AbstractMoveEvent {

    private int cardId;

    public MovePlayWhiteCardEvent() {
    }

    public MovePlayWhiteCardEvent(int cardId) {
        this.cardId = cardId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
