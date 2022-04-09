package ch.silasberger.cardswebproto.event.events.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "value"})
public class WhiteCardCsv {

    private final int id;
    private final String value;

    @JsonCreator
    public WhiteCardCsv(
            @JsonProperty("id") int id,
            @JsonProperty("value") String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
