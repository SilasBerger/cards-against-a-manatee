package ch.silasberger.cardswebproto.game.state.states;


import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.util.ApplicationException;

public abstract class AbstractGameState implements EventHandler {

    protected AbstractGameState nextState;

    public AbstractGameState() {
        this.nextState = this;
    }

    public AbstractGameState transition(AbstractEvent event) throws ApplicationException {
        onEvent(event);
        return nextState;
    }

    protected void fail() {
        // TODO: Set next state to some default error state.
    }
}
