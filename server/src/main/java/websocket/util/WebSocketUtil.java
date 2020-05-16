package websocket.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class WebSocketUtil {

    public static UUID extractPlayerIdAttribute(Map<String, Object> attributes) {
        Object maybePlayerId = attributes.get(WebSocketConstants.PLAYER_ID_ATTRIBUTE_NAME);
        if (maybePlayerId == null) {
            return null;
        }
        try {
            return (UUID) maybePlayerId;
        } catch (ClassCastException ex) {
            return null;
        }
    }

    public static void closeSession(WebSocketSession session, Logger logger) {
        try {
            session.close();
        } catch (IOException ex) {
            logger.error("Failed to close WebSocketSession.");
            ex.printStackTrace();
        }
    }

}
