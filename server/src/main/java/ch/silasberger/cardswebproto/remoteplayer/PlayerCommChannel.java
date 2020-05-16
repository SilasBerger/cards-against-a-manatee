package ch.silasberger.cardswebproto.remoteplayer;

import ch.silasberger.cardswebproto.comm.EventMarshaller;
import ch.silasberger.cardswebproto.comm.JsonEventRepresentation;
import ch.silasberger.cardswebproto.event.EventBusAdapter;
import ch.silasberger.cardswebproto.event.EventChannelNameBroker;
import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.event.events.clienterror.*;
import ch.silasberger.cardswebproto.event.events.gameupdate.AbstractGameUpdateEvent;
import ch.silasberger.cardswebproto.event.events.notification.*;
import io.reactivex.rxjava3.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class PlayerCommChannel implements EventHandler {

    private final Logger logger;
    private final RemotePlayer owner;
    private final EventMarshaller eventMarshaller;
    private final EventBusAdapter eventBusAdapter;
    private final Disposable eventBusSubscription;

    public PlayerCommChannel(RemotePlayer owner) {
        this.owner = owner;
        this.eventMarshaller = new EventMarshaller(owner);
        this.logger = LoggerFactory.getLogger(PlayerCommChannel.class);
        this.eventBusAdapter = owner.getEventBusAdapter();
        this.eventBusSubscription = this.eventBusAdapter.subscribe(this);
    }

    protected void onJsonStringMessage(String jsonString) {
        JsonEventRepresentation jsonEventRepresentation = eventMarshaller.asJsonEventRepresentation(jsonString);
        if (jsonEventRepresentation == null) {
            onBadMessage(jsonString);
            return;
        }

        AbstractEvent event = eventMarshaller.asEvent(jsonEventRepresentation);
        if (event == null) {
            onBadMessage(jsonString);
            return;
        }

        if (owner == null) {
            onBadMessage(jsonString);
            return;
        }

        eventBusAdapter.publishOn(EventChannelNameBroker.getChannelFor(owner), event);
    }

    public void onConnectionClosed() {
        owner.leaveLobby();
    }

    protected abstract void sendJson(String jsonString);

    private void onBadMessage(String jsonString) {
        eventBusAdapter.publishOn(EventChannelNameBroker.getChannelFor(owner), new ErrorBadMessageEvent(jsonString));
    }

    private void forwardToClient(AbstractEvent event) {
        JsonEventRepresentation eventRepresentation = eventMarshaller.asJsonEventRepresentation(event);
        if (eventRepresentation == null) {
            logger.error("Unable to serialize event to JSON representation.");
            return;
        }

        String jsonString = eventMarshaller.asJsonString(eventRepresentation);
        if (jsonString == null) {
            logger.error("Unable to convert JSON representation to JSON string.");
            return;
        }

        sendJson(jsonString);
    }

    // ============== EventHandler ==================

    @Override
    public void onEvent(AbstractEvent event) {
        event.executeOn(this);
    }

    @Override
    public void handle(AbstractNotificationEvent event) {
        forwardToClient(event);
    }

    @Override
    public void handle(AbstractClientErrorEvent event) {
        forwardToClient(event);
    }

    @Override
    public void handle(AbstractGameUpdateEvent event) {
        forwardToClient(event);
    }
}
