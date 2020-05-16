package ch.silasberger.cardswebproto.event.events.notification;

import ch.silasberger.cardswebproto.event.EventHandler;

import java.util.UUID;

public class NotifyNewLeaderEvent extends AbstractNotificationEvent {

    private UUID leaderId;

    public NotifyNewLeaderEvent() {
    }

    public NotifyNewLeaderEvent(UUID leaderId) {
        this.leaderId = leaderId;
    }

    public UUID getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(UUID leaderId) {
        this.leaderId = leaderId;
    }

    @Override
    public void executeOn(EventHandler handler) {
        handler.handle(this);
    }
}
