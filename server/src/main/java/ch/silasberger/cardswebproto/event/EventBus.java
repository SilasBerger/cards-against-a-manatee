package ch.silasberger.cardswebproto.event;

import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class EventBus {

    private final Subject<AbstractEvent> eventSubject;
    private static EventBus instance;

    private EventBus() {
        this.eventSubject = PublishSubject.create();
    }

    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public Subject<AbstractEvent> getEventSubject() {
        return eventSubject;
    }

    public void publish(AbstractEvent event) {
        eventSubject.onNext(event);
    }
}
