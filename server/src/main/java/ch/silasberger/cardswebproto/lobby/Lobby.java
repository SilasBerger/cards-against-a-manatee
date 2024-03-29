package ch.silasberger.cardswebproto.lobby;

import ch.silasberger.cardswebproto.event.EventBusAdapter;
import ch.silasberger.cardswebproto.event.EventChannelNameBroker;
import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.event.events.clienterror.ErrorNotTheLeaderEvent;
import ch.silasberger.cardswebproto.event.events.notification.NotifyGameStartedEvent;
import ch.silasberger.cardswebproto.event.events.notification.NotifyNewLeaderEvent;
import ch.silasberger.cardswebproto.event.events.notification.NotifyPlayerJoinedLobbyEvent;
import ch.silasberger.cardswebproto.event.events.notification.NotifyPlayerLeftLobbyEvent;
import ch.silasberger.cardswebproto.event.events.request.RequestNewGameEvent;
import ch.silasberger.cardswebproto.game.Game;
import ch.silasberger.cardswebproto.model.CardsCollection;
import ch.silasberger.cardswebproto.model.InitialLobbyState;
import ch.silasberger.cardswebproto.model.LobbyMemberRepresentation;
import ch.silasberger.cardswebproto.model.NonceId;
import ch.silasberger.cardswebproto.service.CardsService;
import ch.silasberger.cardswebproto.util.ApplicationException;
import io.reactivex.rxjava3.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Lobby implements EventHandler {

    private final NonceId id;
    private final EventBusAdapter eventBusAdapter;
    private final Disposable eventBusSubscription;
    private final Logger logger;
    private Map<UUID, LobbyMember> members;
    private LobbyMember leader;
    private Game activeGame;

    private final CardsService cardsService;

    public Lobby(NonceId id, CardsService cardsService) {
        this.id = id;
        this.cardsService = cardsService;

        this.logger = LoggerFactory.getLogger(Lobby.class);
        this.members = new HashMap<>();
        this.leader = null;
        this.eventBusAdapter = new EventBusAdapter();
        this.eventBusAdapter.listenOn(EventChannelNameBroker.getChannelFor(this));
        this.eventBusSubscription = this.eventBusAdapter.subscribe(this);
        this.activeGame = null;
    }

    public NonceId getId() {
        return id;
    }

    public InitialLobbyState joinMember(LobbyMember member) {
        if (members.containsKey(member.getMemberId())) {
            return null;
        }
        if (members.size() == 0) {
            leader = member;
        }
        members.put(member.getMemberId(), member);
        eventBusAdapter.listenOn(EventChannelNameBroker.getChannelFor(member));

        AbstractEvent notifyJoinedEvent = new NotifyPlayerJoinedLobbyEvent(LobbyMemberRepresentation.from(member));
        eventBusAdapter.publishOn(localChannel(), notifyJoinedEvent);

        InitialLobbyState initialLobbyState = new InitialLobbyState();
        List<LobbyMemberRepresentation> memberRepresentations = new ArrayList<>(members.size());
        members.values().forEach(m -> memberRepresentations.add(LobbyMemberRepresentation.from(m)));
        initialLobbyState.setLobbyId(id.getValue());
        initialLobbyState.setMembers(memberRepresentations);
        initialLobbyState.setLeaderId(leader.getMemberId());
        initialLobbyState.setGameActive(activeGame != null);
        return initialLobbyState;
    }

    public void exitMember(LobbyMember member) {
        LobbyMember removed = members.remove(member.getMemberId());
        if (removed == null) {
            logger.error("Trying to remove member that isn't part of this lobby.");
            return;
        }

        eventBusAdapter.stopListeningOn(EventChannelNameBroker.getChannelFor(removed));
        eventBusAdapter.publishOn(localChannel(), new NotifyPlayerLeftLobbyEvent(removed.getMemberId()));

        if (removed.equals(leader)) {
            leader = findNewLeader();
            if (leader != null) {
                eventBusAdapter.publishOn(localChannel(), new NotifyNewLeaderEvent(leader.getMemberId()));
            }
            // TODO: Else, collapse lobby?
        }
    }

    private String localChannel() {
        return EventChannelNameBroker.getChannelFor(this);
    }

    private LobbyMember findNewLeader() {
        return members.values().stream().findFirst().orElse(null);
    }

    private boolean isTheLeader(LobbyMember member) {
        if (member == null) {
            return false;
        }
        return member.equals(leader);
    }

    @Override
    public void onEvent(AbstractEvent event) throws ApplicationException {
        event.executeOn(this);
    }

    @Override
    public void handle(RequestNewGameEvent event) throws ApplicationException {
        if (activeGame != null) {
            activeGame.dispose();
        }

        if (!isTheLeader(event.getPlayer())) {
            eventBusAdapter.publishOn(
                    EventChannelNameBroker.getChannelFor(event.getPlayer()),
                    new ErrorNotTheLeaderEvent());
            return;
        }

        CardsCollection cardsCollection = cardsService.loadCardSet("default");
        activeGame = new Game(new HashSet<>(members.values()),
                eventBusAdapter,
                cardsCollection,
                this);

        eventBusAdapter.publishOn(localChannel(), new NotifyGameStartedEvent());
    }
}
