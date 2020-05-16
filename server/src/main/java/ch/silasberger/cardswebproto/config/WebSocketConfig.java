package ch.silasberger.cardswebproto.config;

import ch.silasberger.cardswebproto.remoteplayer.RemotePlayerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import websocket.PlayerWebSocketHandler;
import websocket.util.WebSocketConstants;

import java.util.Map;
import java.util.UUID;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                // TODO: Take this path from some central config location.
                .addHandler(playerWebSocketHandler(), "/player/connect/*")
                .addInterceptors(getInterceptor())
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler playerWebSocketHandler() {
        return new PlayerWebSocketHandler();
    }

    @Bean
    public HttpSessionHandshakeInterceptor getInterceptor() {
        return new HttpSessionHandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                boolean success = super.beforeHandshake(request, response, wsHandler, attributes);
                if (!success) {
                    return false;
                }

                String path = request.getURI().getPath();
                String playerIdString = path.substring(path.lastIndexOf("/") + 1);
                UUID playerId;
                try {
                    playerId = UUID.fromString(playerIdString);
                } catch (IllegalArgumentException ex) {
                    // Not a valid UUID.
                    response.setStatusCode(HttpStatus.BAD_REQUEST);
                    return false;
                }

                if (!RemotePlayerRegistry.getInstance().exists(playerId)) {
                    // User ID is valid but user not found.
                    response.setStatusCode(HttpStatus.NOT_FOUND);
                    return false;
                }

                // User found, success.
                response.setStatusCode(HttpStatus.OK);
                attributes.put(WebSocketConstants.PLAYER_ID_ATTRIBUTE_NAME, playerId);
                return true;
            }
        };
    }
}
