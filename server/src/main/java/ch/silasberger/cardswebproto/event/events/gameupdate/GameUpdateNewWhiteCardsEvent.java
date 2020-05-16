package ch.silasberger.cardswebproto.event.events.gameupdate;

import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.model.WhiteCardRepresentation;

import java.util.List;

public class GameUpdateNewWhiteCardsEvent extends AbstractGameUpdateEvent {

    private List<WhiteCardRepresentation> cards;

    public GameUpdateNewWhiteCardsEvent() {
    }

    public GameUpdateNewWhiteCardsEvent(List<WhiteCardRepresentation> cards) {
        this.cards = cards;
    }

    public List<WhiteCardRepresentation> getCards() {
        return cards;
    }

    public void setCards(List<WhiteCardRepresentation> cards) {
        this.cards = cards;
    }

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
