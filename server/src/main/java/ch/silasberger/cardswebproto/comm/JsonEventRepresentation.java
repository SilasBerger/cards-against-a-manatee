package ch.silasberger.cardswebproto.comm;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonEventRepresentation {

    private JsonEventHeaders headers;
    private JsonNode data;

    public JsonEventRepresentation() {
    }

    public JsonEventRepresentation(JsonEventHeaders headers, JsonNode data) {
        this.headers = headers;
        this.data = data;
    }

    public JsonEventHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(JsonEventHeaders headers) {
        this.headers = headers;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }
}
