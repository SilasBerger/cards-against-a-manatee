package websocket;

import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.remoteplayer.PlayerCommChannel;
import ch.silasberger.cardswebproto.remoteplayer.RemotePlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class WebSocketPlayerCommChannel extends PlayerCommChannel {

    private final WebSocketSession session;
    private final Logger logger;

    public WebSocketPlayerCommChannel(RemotePlayer owner, WebSocketSession session) {
        super(owner);
        this.session = session;
        this.logger = LoggerFactory.getLogger(WebSocketPlayerCommChannel.class);
    }

    public void onMessage(TextMessage message) {
        onJsonStringMessage(message.getPayload());
    }

    @Override
    protected void sendJson(String jsonString) {
        if (!session.isOpen()) {
            return;
        }
        try {
            session.sendMessage(new TextMessage(jsonString));
        } catch (IOException e) {
            logger.error("Unable to send WebSocket message.", e);
        }
    }
}
