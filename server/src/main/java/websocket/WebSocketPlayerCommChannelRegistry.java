package websocket;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

public class WebSocketPlayerCommChannelRegistry {

    private static WebSocketPlayerCommChannelRegistry instance;

    private Map<WebSocketSession, WebSocketPlayerCommChannel> channelMap;

    private WebSocketPlayerCommChannelRegistry() {
        channelMap = new HashMap<>();
    }

    public static WebSocketPlayerCommChannelRegistry getInstance() {
        if (instance == null) {
            instance = new WebSocketPlayerCommChannelRegistry();
        }
        return instance;
    }

    public boolean register(WebSocketSession webSocketSession, WebSocketPlayerCommChannel commChannel) {
        channelMap.put(webSocketSession, commChannel);
        return true;
    }

    public boolean unregister(WebSocketSession webSocketSession) {
        WebSocketPlayerCommChannel removed = channelMap.remove(webSocketSession);
        return removed != null;
    }

    public boolean unregister(WebSocketPlayerCommChannel commChannel) {
        Map.Entry<WebSocketSession, WebSocketPlayerCommChannel> match = channelMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(commChannel))
                .findFirst()
                .orElseGet(() -> null);
        if (match == null) {
            return false;
        }
        WebSocketPlayerCommChannel removed = channelMap.remove(match.getKey());
        return removed != null;
    }

    public WebSocketPlayerCommChannel get(WebSocketSession webSocketSession) {
        return channelMap.get(webSocketSession);
    }


}
