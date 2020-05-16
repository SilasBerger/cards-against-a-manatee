package ch.silasberger.cardswebproto;

import ch.silasberger.cardswebproto.event.EventTypeProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardsOnTheWebPrototypeApplication {

    public static void main(String[] args) {
        eagerLoad();
        SpringApplication.run(CardsOnTheWebPrototypeApplication.class, args);
    }

    private static void eagerLoad() {
        // Force specific classes to load eagerly.
        EventTypeProvider.getInstance();
    }
}
