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

public interface EventHandler {
    void onEvent(AbstractEvent event);

    // Yes, these calls will get dispatched about half a dozen times just to end up in a no-op. I don't care. Go away.

    // Default handlers.
    default void handle(AbstractEvent event) {}

    // Request events.
    default void handle(AbstractRequestEvent event) { handle((AbstractEvent) event); }
    default void handle(RequestJoinLobbyEvent event) { handle((AbstractRequestEvent) event); }
    default void handle(RequestExitLobbyEvent event) { handle((AbstractRequestEvent) event); }
    default void handle(RequestNewGameEvent event) { handle((AbstractRequestEvent) event); }

    // Notification events.
    default void handle(AbstractNotificationEvent event) { handle((AbstractEvent) event); }
    default void handle(NotifyCurrentLobbyStateEvent event) { handle((AbstractNotificationEvent) event); }
    default void handle(NotifyPlayerJoinedLobbyEvent event) { handle((AbstractNotificationEvent) event); }
    default void handle(NotifyExitedLobbyEvent event) { handle((AbstractNotificationEvent) event); }
    default void handle(NotifyPlayerLeftLobbyEvent event) { handle((AbstractNotificationEvent) event); }
    default void handle(NotifyNewLeaderEvent event) { handle((AbstractNotificationEvent) event); }
    default void handle(NotifyGameStartedEvent event) { handle((AbstractNotificationEvent) event); }

    // Client error events.
    default void handle(AbstractClientErrorEvent event) { handle((AbstractEvent) event); }
    default void handle(ErrorAlreadyInALobbyEvent event) { handle((AbstractClientErrorEvent) event); }
    default void handle(ErrorNoSuchLobbyEvent event) { handle((AbstractClientErrorEvent) event); }
    default void handle(ErrorNotInALobbyEvent event) { handle((AbstractClientErrorEvent) event); }
    default void handle(ErrorUnableToJoinLobbyEvent event) { handle((AbstractClientErrorEvent) event); }
    default void handle(ErrorBadMessageEvent event) { handle((AbstractClientErrorEvent) event); }
    default void handle(ErrorNotTheLeaderEvent event) { handle((AbstractClientErrorEvent) event); }

    // Game request events.
    default void handle(AbstractGameRequestEvent event) { handle((AbstractEvent) event); }
    default void handle(GameRequestStartGameEvent event) { handle((AbstractGameRequestEvent) event); }
    default void handle(GameRequestNextTurnEvent event) { handle((AbstractGameRequestEvent) event); }

    // Move events.
    default void handle(AbstractMoveEvent event) { handle((AbstractEvent) event); }
    default void handle(MovePlayWhiteCardEvent event) { handle((AbstractMoveEvent) event); }

    // Game update events.
    default void handle(AbstractGameUpdateEvent event) { handle((AbstractEvent) event); }
    default void handle(GameUpdatePlayedWhiteCardEvent event) { handle((AbstractGameUpdateEvent) event); }
    default void handle(GameUpdateNewBlackCardEvent event) { handle((AbstractGameUpdateEvent) event); }
    default void handle(GameUpdateNewWhiteCardsEvent event) { handle((AbstractGameUpdateEvent) event); }
    default void handle(GameUpdateTurnOverEvent event) { handle((AbstractGameUpdateEvent) event); }
    default void handle(GameUpdateGameOverEvent event) { handle((AbstractGameUpdateEvent) event); }
}
