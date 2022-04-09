package ch.silasberger.cardswebproto.remoteplayer;

import ch.silasberger.cardswebproto.event.EventBusAdapter;
import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.event.EventChannelNameBroker;
import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.event.events.clienterror.ErrorAlreadyInALobbyEvent;
import ch.silasberger.cardswebproto.event.events.clienterror.ErrorNoSuchLobbyEvent;
import ch.silasberger.cardswebproto.event.events.clienterror.ErrorNotInALobbyEvent;
import ch.silasberger.cardswebproto.event.events.clienterror.ErrorUnableToJoinLobbyEvent;
import ch.silasberger.cardswebproto.event.events.notification.NotifyCurrentLobbyStateEvent;
import ch.silasberger.cardswebproto.event.events.notification.NotifyExitedLobbyEvent;
import ch.silasberger.cardswebproto.event.events.request.RequestExitLobbyEvent;
import ch.silasberger.cardswebproto.event.events.request.RequestJoinLobbyEvent;
import ch.silasberger.cardswebproto.lobby.Lobby;
import ch.silasberger.cardswebproto.lobby.LobbyBroker;
import ch.silasberger.cardswebproto.lobby.LobbyMember;
import ch.silasberger.cardswebproto.model.InitialLobbyState;
import ch.silasberger.cardswebproto.util.ApplicationException;
import io.reactivex.rxjava3.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.UUID;

public class RemotePlayer implements LobbyMember, EventHandler {

    private final Logger logger;

    private final UUID id;
    private final EventBusAdapter eventBusAdapter;
    private Disposable eventBusSubscription;
    private String name;
    private PlayerCommChannel commChannel;
    private Lobby currentLobby;


    public RemotePlayer(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.logger = LoggerFactory.getLogger(RemotePlayer.class);
        this.eventBusAdapter = new EventBusAdapter();
        initEventBusAdapter();
    }

    private void initEventBusAdapter() {
        this.eventBusSubscription = eventBusAdapter.subscribe(this);
        eventBusAdapter.listenOn(EventChannelNameBroker.getChannelFor(this));
    }

    private String localChannel() {
        return EventChannelNameBroker.getChannelFor(this);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommChannel(PlayerCommChannel commChannel) {
        this.commChannel = commChannel;
    }

    public EventBusAdapter getEventBusAdapter() {
        return eventBusAdapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemotePlayer that = (RemotePlayer) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ============================ LobbyMember ==========================

    @Override
    public UUID getMemberId() {
        return id;
    }

    @Override
    public String getMemberName() {
        return name;
    }

    // ========================= EventHandler =======================
    @Override
    public void onEvent(AbstractEvent event) throws ApplicationException {
        event.executeOn(this);
    }

    @Override
    public void handle(RequestJoinLobbyEvent event) {
        if (currentLobby != null) {
            eventBusAdapter.publishOn(localChannel(), new ErrorAlreadyInALobbyEvent(currentLobby.getId()));
            return;
        }
        Lobby maybeLobby = LobbyBroker.getInstance().get(event.getLobbyId());
        if (maybeLobby == null) {
            eventBusAdapter.publishOn(localChannel(), new ErrorNoSuchLobbyEvent(event.getLobbyId()));
            return;
        }

        currentLobby = maybeLobby;
        InitialLobbyState initialLobbyState = maybeLobby.joinMember(this);
        if (initialLobbyState == null) {
            eventBusAdapter.publishOn(localChannel(), new ErrorUnableToJoinLobbyEvent(event.getLobbyId()));
            return;
        }

        eventBusAdapter.listenOn(EventChannelNameBroker.getChannelFor(currentLobby));
        eventBusAdapter.publishOn(localChannel(), new NotifyCurrentLobbyStateEvent(initialLobbyState));
    }

    @Override
    public void handle(RequestExitLobbyEvent event) {
        leaveLobby();
    }

    public void leaveLobby() {
        if (currentLobby == null) {
            eventBusAdapter.publishOn(localChannel(), new ErrorNotInALobbyEvent());
            return;
        }
        eventBusAdapter.stopListeningOn(EventChannelNameBroker.getChannelFor(currentLobby));
        currentLobby.exitMember(this);
        currentLobby = null;
        eventBusAdapter.publishOn(localChannel(), new NotifyExitedLobbyEvent());
    }
}
