package ch.silasberger.cardswebproto.event.events.request;

import ch.silasberger.cardswebproto.event.EventHandler;
import com.fasterxml.jackson.databind.JsonNode;

public class RequestNewGameEvent extends AbstractRequestEvent {

    private String gameModeName;
    private JsonNode ruleSet;

    public RequestNewGameEvent() {
    }

    public RequestNewGameEvent(String gameModeName, JsonNode ruleSet) {
        this.gameModeName = gameModeName;
        this.ruleSet = ruleSet;
    }

    public String getGameModeName() {
        return gameModeName;
    }

    public void setGameModeName(String gameModeName) {
        this.gameModeName = gameModeName;
    }

    public JsonNode getRuleSet() {
        return ruleSet;
    }

    public void setRuleSet(JsonNode ruleSet) {
        this.ruleSet = ruleSet;
    }

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
