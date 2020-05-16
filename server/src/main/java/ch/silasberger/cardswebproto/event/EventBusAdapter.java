package ch.silasberger.cardswebproto.event;

import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;


public class EventBusAdapter {

    private final Disposable busSubscription;
    private final Subject<AbstractEvent> eventSubject;
    private final Set<String> channels;
    private final Logger logger;

    public EventBusAdapter() {
        this.eventSubject = PublishSubject.create();
        this.channels = new HashSet<>();
        this.busSubscription = initBusSubscription();
        this.logger = LoggerFactory.getLogger(EventBusAdapter.class);
    }

    private Disposable initBusSubscription() {
        return EventBus.getInstance()
                .getEventSubject()
                .filter(event -> this.channels.contains(event.getHeaders().getSourceChannel()))
                .subscribe(this::onEvent);
    }

    public void onEvent(AbstractEvent event) {
        eventSubject.onNext(event);
    }

    public void listenOn(String channel) {
        channels.add(channel);
    }

    public void stopListeningOn(String channel) {
        channels.remove(channel);
    }

    public void publishOn(String channel, AbstractEvent event) {
        if (event.getHeaders() == null) {
            logger.error("Headers not set on event of type " + event.getClass().getSimpleName() + " - ignoring.");
            return;
        }
        event.getHeaders().setSourceChannel(channel);
        EventBus.getInstance().publish(event);
    }

    public Disposable subscribe(EventHandler observer) {
        return eventSubject.subscribe(observer::onEvent);
    }

    public void dispose() {
        busSubscription.dispose();
    }
}
