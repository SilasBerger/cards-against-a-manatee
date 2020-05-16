package ch.silasberger.cardswebproto.event.events.clienterror;

import ch.silasberger.cardswebproto.event.EventHandler;

public class ErrorBadMessageEvent extends AbstractClientErrorEvent {

    private String originalMessage;

    public ErrorBadMessageEvent() {
    }

    public ErrorBadMessageEvent(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
