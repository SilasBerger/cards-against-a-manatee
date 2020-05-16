package ch.silasberger.cardswebproto.game.state.states;


import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.event.events.AbstractEvent;

public abstract class AbstractGameState implements EventHandler {

    protected AbstractGameState nextState;

    public AbstractGameState() {
        this.nextState = this;
    }

    public AbstractGameState transition(AbstractEvent event) {
        onEvent(event);
        return nextState;
    }

    protected void fail() {
        // TODO: Set next state to some default error state.
    }
}
