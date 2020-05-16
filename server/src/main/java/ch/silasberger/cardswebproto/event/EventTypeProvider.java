package ch.silasberger.cardswebproto.event;

import ch.silasberger.cardswebproto.event.events.AbstractEvent;
import ch.silasberger.cardswebproto.util.ReflectionUtils;
import org.reflections.Reflections;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class EventTypeProvider {

    private static final String EVENT_CLASSES_ROOT_PACKAGE = "events";
    private static EventTypeProvider instance;

    private final Map<String, Class<? extends AbstractEvent>> eventTypeMap;

    private EventTypeProvider() {
        this.eventTypeMap = new Hashtable<>();
        loadEventTypeDefinitions();
    }

    public static EventTypeProvider getInstance() {
        if (instance == null) {
            instance = new EventTypeProvider();
        }
        return instance;
    }

    public Class<? extends AbstractEvent> get(String canonicalEventName) {
        return eventTypeMap.get(canonicalEventName);
    }

    private void loadEventTypeDefinitions() {
        String reflectionRoot = this.getClass().getPackageName() + "." + EVENT_CLASSES_ROOT_PACKAGE;
        Reflections reflections = new Reflections(reflectionRoot);
        Set<Class<? extends AbstractEvent>> eventClasses = reflections.getSubTypesOf(AbstractEvent.class);
        eventClasses.forEach(clazz -> eventTypeMap.put(canonicalName(clazz), clazz));
    }

    public static String canonicalName(Class<? extends AbstractEvent> clazz) {
        String clazzName = ReflectionUtils.ClassName.qualifiedAfter(clazz, EVENT_CLASSES_ROOT_PACKAGE);
        return ReflectionUtils.ClassName.formatClassName(clazzName, "[Ee]vent");
    }

}
