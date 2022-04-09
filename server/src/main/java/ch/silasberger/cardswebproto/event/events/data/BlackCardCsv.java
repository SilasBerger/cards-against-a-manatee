package ch.silasberger.cardswebproto.event.events.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "value", "pick"})
public class BlackCardCsv {

    private final int id;
    private final String value;
    private final int pick;

    @JsonCreator
    public BlackCardCsv(
            @JsonProperty("id") int id,
            @JsonProperty("value") String value,
            @JsonProperty("pick") int pick) {
        this.id = id;
        this.value = value;
        this.pick = pick;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public int getPick() {
        return pick;
    }
}
