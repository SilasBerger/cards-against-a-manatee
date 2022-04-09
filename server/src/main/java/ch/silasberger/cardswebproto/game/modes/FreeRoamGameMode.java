package ch.silasberger.cardswebproto.game.modes;

import ch.silasberger.cardswebproto.event.EventBusAdapter;
import ch.silasberger.cardswebproto.event.EventChannelNameBroker;
import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.event.events.gamerequest.GameRequestNextTurnEvent;
import ch.silasberger.cardswebproto.event.events.gamerequest.GameRequestStartGameEvent;
import ch.silasberger.cardswebproto.event.events.gameupdate.*;
import ch.silasberger.cardswebproto.event.events.move.MovePlayWhiteCardEvent;
import ch.silasberger.cardswebproto.event.events.notification.NotifyPlayerLeftLobbyEvent;
import ch.silasberger.cardswebproto.game.state.states.AbstractGameState;
import ch.silasberger.cardswebproto.lobby.Lobby;
import ch.silasberger.cardswebproto.lobby.LobbyMember;
import ch.silasberger.cardswebproto.model.BlackCardRepresentation;
import ch.silasberger.cardswebproto.model.CardsCollection;
import ch.silasberger.cardswebproto.model.WhiteCardRepresentation;
import ch.silasberger.cardswebproto.remoteplayer.RemotePlayerRegistry;
import ch.silasberger.cardswebproto.util.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class FreeRoamGameMode extends AbstractGameMode {

    // TODO: Some things we need here: run forever or run n rounds, #cards per round.

    private final Logger logger;
    private final CardsCollection cardsCollection;
    private final int NUM_TURNS = 10;
    private int currentTurn;

    public FreeRoamGameMode(Lobby lobby, Set<LobbyMember> participants, CardsCollection cardsCollection, EventBusAdapter eventBusAdapter) {
        super(participants, eventBusAdapter, lobby);
        this.cardsCollection = cardsCollection;
        this.logger = LoggerFactory.getLogger(FreeRoamGameMode.class);
        this.currentTurn = 0;
        setCurrentState(new GameReadyState());
    }

    private class GameReadyState extends AbstractGameState {

        public GameReadyState() {
            currentTurn = 0;
        }

        @Override
        public void onEvent(AbstractEvent event) throws ApplicationException {
            event.executeOn(this);
        }

        @Override
        public void handle(GameRequestStartGameEvent event) {
            boolean hasEnoughWhiteCards = cardsCollection.hasNextManyWhite(participants.size() * NUM_TURNS);
            boolean hasEnoughBlackCards = cardsCollection.hasNextManyBlack(NUM_TURNS);
            if (!hasEnoughBlackCards || !hasEnoughWhiteCards) {
                // TODO: Throw new event.
                // TODO: Handle this case somehow.
            }

            participants.forEach(participant -> {
                List<WhiteCardRepresentation> whiteCards = cardsCollection.nextManyWhite(NUM_TURNS)
                        .stream()
                        .map(WhiteCardRepresentation::from)
                        .collect(Collectors.toList());
                AbstractEvent newWhiteCardsEvent = new GameUpdateNewWhiteCardsEvent(whiteCards);
                eventBusAdapter.publishOn(EventChannelNameBroker.getChannelFor(participant), newWhiteCardsEvent);
            });

            nextState = new TurnActiveState();
        }
    }

    private class TurnReadyState extends AbstractGameState {

        @Override
        public void onEvent(AbstractEvent event) throws ApplicationException {
            event.executeOn(this);
        }

        @Override
        public void handle(GameRequestNextTurnEvent event) {
            nextState = new TurnActiveState();
        }
    }

    private class TurnActiveState extends AbstractGameState {

        private Map<LobbyMember, Integer> expectedCounts;

        public TurnActiveState() {
            currentTurn++;
            expectedCounts = new Hashtable<>();
            // TODO: Use rule-defined count here. In fact, use the black card's pick...
            participants.forEach(participant -> expectedCounts.put(participant, 1));
            BlackCardRepresentation blackCard = BlackCardRepresentation.from(cardsCollection.nextBlack());
            AbstractEvent blackCardEvent = new GameUpdateNewBlackCardEvent(blackCard);
            eventBusAdapter.publishOn(EventChannelNameBroker.getChannelFor(lobby), blackCardEvent);
        }

        @Override
        public void onEvent(AbstractEvent event) throws ApplicationException {
            event.executeOn(this);
        }

        @Override
        public void handle(MovePlayWhiteCardEvent event) {
            cardsCollection.findWhiteById(event.getCardId())
                    .ifPresent(whiteCard -> {
                        expectedCounts.put(event.getPlayer(), expectedCounts.get(event.getPlayer()) - 1);
                        WhiteCardRepresentation wcr = new WhiteCardRepresentation(whiteCard.getId(), whiteCard.getValue());
                        AbstractEvent notifyEvent = new GameUpdatePlayedWhiteCardEvent(wcr, event.getPlayer().getId());
                        eventBusAdapter.publishOn(EventChannelNameBroker.getChannelFor(lobby), notifyEvent);
                    }); // TODO: Error handling.
            processTurnUpdate();
        }

        @Override
        public void handle(NotifyPlayerLeftLobbyEvent event) {
            LobbyMember quitter = RemotePlayerRegistry.getInstance().get(event.getPlayerId());
            if (quitter == null) {
                // TODO: Error handling.
            }
            expectedCounts.remove(quitter);
            processTurnUpdate();
        }

        private boolean isTurnFinished() {
            Optional<Integer> remaining = expectedCounts.values()
                    .stream()
                    .filter(count -> count > 0)
                    .findFirst();
            return remaining.isEmpty();
        }

        private void processTurnUpdate() {
            if (!isTurnFinished()) {
                // Turn is not over yet - no state change, no events.
                nextState = this;
                return;
            }

            // Turn is over - change state, send event.
            AbstractEvent updateEvent;
            if (currentTurn >= NUM_TURNS) {
                // Max number of turns reached, game over - get ready for next game.
                nextState = new GameReadyState();
                updateEvent = new GameUpdateGameOverEvent();
            } else {
                // Turn over, but game still running - get ready for next turn.
                nextState = new TurnReadyState();
                updateEvent = new GameUpdateTurnOverEvent();
            }
            eventBusAdapter.publishOn(EventChannelNameBroker.getChannelFor(lobby), updateEvent);
        }
    }

}
