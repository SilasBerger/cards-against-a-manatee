package ch.silasberger.cardswebproto.game;

import ch.silasberger.cardswebproto.event.EventBusAdapter;
import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.event.events.notification.NotifyPlayerLeftLobbyEvent;
import ch.silasberger.cardswebproto.game.modes.AbstractGameMode;
import ch.silasberger.cardswebproto.game.modes.FreeRoamGameMode;
import ch.silasberger.cardswebproto.lobby.Lobby;
import ch.silasberger.cardswebproto.lobby.LobbyMember;
import ch.silasberger.cardswebproto.model.BlackCard;
import ch.silasberger.cardswebproto.model.CardsCollection;
import ch.silasberger.cardswebproto.model.WhiteCard;
import io.reactivex.rxjava3.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

public class Game implements EventHandler {

    private final String gameModeName;
    private final Set<LobbyMember> participants;
    private final EventBusAdapter eventBusAdapter;
    private final Disposable eventBusSubscription;
    private final Logger logger;
    private final Lobby lobby;
    private final AbstractGameMode gameMode;
    private final CardsCollection cardsCollection;

    public Game(String gameModeName, Set<LobbyMember> participants, EventBusAdapter eventBusAdapter, CardsCollection cardsCollection, Lobby lobby) {
        this.gameModeName = gameModeName;
        this.participants = participants;
        this.eventBusAdapter = eventBusAdapter;
        this.eventBusSubscription = eventBusAdapter.subscribe(this);
        this.lobby = lobby;
        this.logger = LoggerFactory.getLogger(Game.class);
        this.cardsCollection = cardsCollection;
        this.gameMode = new FreeRoamGameMode(lobby, participants, cardsCollection, eventBusAdapter);
    }

    public String getGameModeName() {
        return gameModeName;
    }

    public Set<LobbyMember> getParticipants() {
        return participants;
    }

    // ================== Helpers ======================
    public Optional<LobbyMember> findPlayerById(UUID playerId) {
        return participants.stream()
                .filter(member -> member.getMemberId().equals(playerId))
                .findFirst();
    }

    // ================== EventHandler =================
    @Override
    public void onEvent(AbstractEvent event) {
        event.executeOn(this);
    }

    @Override
    public void handle(NotifyPlayerLeftLobbyEvent event) {
        findPlayerById(event.getPlayerId()).ifPresent(participants::remove);
    }

    public void dispose() {
        gameMode.dispose();
    }
}
