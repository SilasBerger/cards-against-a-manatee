package websocket;

import ch.silasberger.cardswebproto.remoteplayer.RemotePlayer;
import ch.silasberger.cardswebproto.remoteplayer.RemotePlayerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import websocket.util.WebSocketUtil;

import java.io.IOException;
import java.util.UUID;

public class PlayerWebSocketHandler extends TextWebSocketHandler {

    private Logger logger;

    public PlayerWebSocketHandler() {
        this.logger = LoggerFactory.getLogger(PlayerWebSocketHandler.class);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        UUID playerId = WebSocketUtil.extractPlayerIdAttribute(session.getAttributes());
        if (playerId == null) {
            // Missing playerId attribute in session.
            handlePlayerNotFound(session);
            return;
        }

        RemotePlayer player = RemotePlayerRegistry.getInstance().get(playerId);
        if (player == null) {
            // Have playerId attribute, but no such RC player found in registry.
            handlePlayerNotFound(session);
            return;
        }

        WebSocketPlayerCommChannel commChannel = new WebSocketPlayerCommChannel(player, session);
        player.setCommChannel(commChannel);
        WebSocketPlayerCommChannelRegistry.getInstance().register(session, commChannel);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        WebSocketPlayerCommChannel channel = WebSocketPlayerCommChannelRegistry.getInstance().get(session);

        if (channel == null) {
            handlePlayerNotFound(session);
            return;
        }

        channel.onMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        WebSocketPlayerCommChannel channel = WebSocketPlayerCommChannelRegistry.getInstance().get(session);

        if (channel == null) {
            logger.error("WS session closed, but wasn't associated with a comm channel.");
            return;
        }

        channel.onConnectionClosed();
    }

    private void handlePlayerNotFound(WebSocketSession session) {
        UUID playerId = WebSocketUtil.extractPlayerIdAttribute(session.getAttributes());
        if (playerId == null) {
            logger.error("WebSocketSession not associated with comm channel, and has no player Id.");
        } else {
            logger.error("No channel found for session assigned to user ID " + playerId + ".");
        }
        // TODO: Send error message to client.
        WebSocketUtil.closeSession(session, logger);
    }
}
