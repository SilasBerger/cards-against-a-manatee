package ch.silasberger.cardswebproto.event;

import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.event.events.clienterror.*;
import ch.silasberger.cardswebproto.event.events.gamerequest.AbstractGameRequestEvent;
import ch.silasberger.cardswebproto.event.events.gamerequest.GameRequestNextTurnEvent;
import ch.silasberger.cardswebproto.event.events.gamerequest.GameRequestStartGameEvent;
import ch.silasberger.cardswebproto.event.events.gameupdate.*;
import ch.silasberger.cardswebproto.event.events.move.AbstractMoveEvent;
import ch.silasberger.cardswebproto.event.events.move.MovePlayWhiteCardEvent;
import ch.silasberger.cardswebproto.event.events.notification.*;
import ch.silasberger.cardswebproto.event.events.request.AbstractRequestEvent;
import ch.silasberger.cardswebproto.event.events.request.RequestExitLobbyEvent;
import ch.silasberger.cardswebproto.event.events.request.RequestJoinLobbyEvent;
import ch.silasberger.cardswebproto.event.events.request.RequestNewGameEvent;
import ch.silasberger.cardswebproto.util.ReflectionUtils;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class EventTypeProvider {

    private static final String EVENT_CLASSES_ROOT_PACKAGE = "events";
    private static EventTypeProvider instance;

    private final Map<String, Class<? extends AbstractEvent>> eventTypeMap;

    private EventTypeProvider() {
        this.eventTypeMap = new Hashtable<>();
        loadEventTypeDefinitions();
    }

    public static EventTypeProvider getInstance() {
        if (instance == null) {
            instance = new EventTypeProvider();
        }
        return instance;
    }

    public Class<? extends AbstractEvent> get(String canonicalEventName) {
        return eventTypeMap.get(canonicalEventName);
    }

    private void loadEventTypeDefinitions() {
        Set<Class<? extends AbstractEvent>> eventClasses = collectEventClasses();
        eventClasses.forEach(clazz -> eventTypeMap.put(canonicalName(clazz), clazz));
    }

    private Set<Class<? extends AbstractEvent>> collectEventClasses() {
        Set<Class<? extends AbstractEvent>> eventClasses = new HashSet<>();
        // Client error
        eventClasses.add(AbstractClientErrorEvent.class);
        eventClasses.add(ErrorAlreadyInALobbyEvent.class);
        eventClasses.add(ErrorBadMessageEvent.class);
        eventClasses.add(ErrorNoSuchLobbyEvent.class);
        eventClasses.add(ErrorNotInALobbyEvent.class);
        eventClasses.add(ErrorNotTheLeaderEvent.class);
        eventClasses.add(ErrorUnableToJoinLobbyEvent.class);

        // Game request
        eventClasses.add(AbstractGameRequestEvent.class);
        eventClasses.add(GameRequestNextTurnEvent.class);
        eventClasses.add(GameRequestStartGameEvent.class);

        // Game update
        eventClasses.add(AbstractGameUpdateEvent.class);
        eventClasses.add(GameUpdateGameOverEvent.class);
        eventClasses.add(GameUpdateNewBlackCardEvent.class);
        eventClasses.add(GameUpdateNewWhiteCardsEvent.class);
        eventClasses.add(GameUpdatePlayedWhiteCardEvent.class);
        eventClasses.add(GameUpdateTurnOverEvent.class);

        // Move
        eventClasses.add(AbstractMoveEvent.class);
        eventClasses.add(MovePlayWhiteCardEvent.class);

        // Notification
        eventClasses.add(AbstractNotificationEvent.class);
        eventClasses.add(NotifyCurrentLobbyStateEvent.class);
        eventClasses.add(NotifyExitedLobbyEvent.class);
        eventClasses.add(NotifyGameStartedEvent.class);
        eventClasses.add(NotifyNewLeaderEvent.class);
        eventClasses.add(NotifyPlayerJoinedLobbyEvent.class);
        eventClasses.add(NotifyPlayerLeftLobbyEvent.class);

        // Request
        eventClasses.add(AbstractRequestEvent.class);
        eventClasses.add(RequestExitLobbyEvent.class);
        eventClasses.add(RequestJoinLobbyEvent.class);
        eventClasses.add(RequestNewGameEvent.class);

        return eventClasses;
    }

    public static String canonicalName(Class<? extends AbstractEvent> clazz) {
        String clazzName = ReflectionUtils.ClassName.qualifiedAfter(clazz, EVENT_CLASSES_ROOT_PACKAGE);
        return ReflectionUtils.ClassName.formatClassName(clazzName, "[Ee]vent");
    }

}
