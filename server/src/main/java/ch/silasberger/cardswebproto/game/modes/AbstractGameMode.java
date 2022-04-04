package ch.silasberger.cardswebproto.game.modes;

import ch.silasberger.cardswebproto.event.EventBusAdapter;
import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.game.state.states.AbstractGameState;
import ch.silasberger.cardswebproto.lobby.Lobby;
import ch.silasberger.cardswebproto.lobby.LobbyMember;
import ch.silasberger.cardswebproto.util.ApplicationException;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.Set;

public abstract class AbstractGameMode implements EventHandler {

    protected final Set<LobbyMember> participants;
    protected final EventBusAdapter eventBusAdapter;
    private final Disposable eventBusSubscription;
    private AbstractGameState currentState;
    protected final Lobby lobby;

    public AbstractGameMode(Set<LobbyMember> participants, EventBusAdapter eventBusAdapter, Lobby lobby) {
        this.participants = participants;
        this.eventBusAdapter = eventBusAdapter;
        this.eventBusSubscription = eventBusAdapter.subscribe(this);
        this.lobby = lobby;
    }

    protected AbstractGameState getCurrentState() {
        return currentState;
    }

    protected void setCurrentState(AbstractGameState currentState) {
        this.currentState = currentState;
    }

    @Override
    public void onEvent(AbstractEvent event) throws ApplicationException {
        currentState = currentState.transition(event);
    }

    public void dispose() {
        eventBusSubscription.dispose();
    }
}
