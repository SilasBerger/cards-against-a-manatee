package ch.silasberger.cardswebproto.comm;

import ch.silasberger.cardswebproto.event.EventHandler;
import ch.silasberger.cardswebproto.event.EventHeaders;
import ch.silasberger.cardswebproto.event.EventTypeProvider;
import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.event.events.move.AbstractMoveEvent;
import ch.silasberger.cardswebproto.event.events.request.AbstractRequestEvent;
import ch.silasberger.cardswebproto.remoteplayer.RemotePlayer;
import ch.silasberger.cardswebproto.util.ApplicationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventMarshaller {

    private final ObjectMapper objectMapper;
    private final EventPostProcessor eventPostProcessor;
    private final Logger logger;

    public EventMarshaller(RemotePlayer owner) {
        this.eventPostProcessor = new EventPostProcessor(owner);
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.logger = LoggerFactory.getLogger(EventMarshaller.class);
    }

    public JsonEventRepresentation asJsonEventRepresentation(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, JsonEventRepresentation.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public JsonEventRepresentation asJsonEventRepresentation(AbstractEvent event) {
        String eventName = EventTypeProvider.canonicalName(event.getClass());
        JsonEventHeaders jsonHeaders = new JsonEventHeaders(eventName, event.getHeaders().getIat());
        try {
            JsonNode data = objectMapper.valueToTree(event);
            return new JsonEventRepresentation(jsonHeaders, data);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String asJsonString(JsonEventRepresentation jsonEventRepresentation) {
        try {
            return objectMapper.writeValueAsString(jsonEventRepresentation);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public AbstractEvent asEvent(JsonEventRepresentation jsonEventRepresentation) {
        JsonEventHeaders jsonHeaders = jsonEventRepresentation.getHeaders();
        if (jsonHeaders == null) {
            // Failed to parse JSON headers.
            return null;
        }

        Class<? extends AbstractEvent> eventClazz = EventTypeProvider.getInstance().get(jsonHeaders.getEventName());
        if (eventClazz == null) {
            // No such command.
            return null;
        }

        try {
            AbstractEvent event = objectMapper.treeToValue(jsonEventRepresentation.getData(), eventClazz);
            EventHeaders eventHeaders = new EventHeaders(jsonEventRepresentation.getHeaders().getIat());
            event.setHeaders(eventHeaders);
            eventPostProcessor.onEvent(event);
            return event;
        } catch (JsonProcessingException e) {
            return null;
        } catch (ApplicationException e) {
            logger.error(e.toString());
            return null;
        }
    }

    private static class EventPostProcessor implements EventHandler {

        private final RemotePlayer owner;

        private EventPostProcessor(RemotePlayer owner) {
            this.owner = owner;
        }

        @Override
        public void onEvent(AbstractEvent event) throws ApplicationException {
            event.executeOn(this);
        }

        @Override
        public void handle(AbstractRequestEvent event) {
            event.setPlayer(owner);
        }

        @Override
        public void handle(AbstractMoveEvent event) {
            event.setPlayer(owner);
        }
    }


}
