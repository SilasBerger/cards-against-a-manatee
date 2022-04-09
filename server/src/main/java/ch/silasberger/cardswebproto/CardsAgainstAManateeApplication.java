package ch.silasberger.cardswebproto;

import ch.silasberger.cardswebproto.event.EventTypeProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardsAgainstAManateeApplication {

    public static void main(String[] args) {
        eagerLoad();
        SpringApplication.run(CardsAgainstAManateeApplication.class, args);
    }

    private static void eagerLoad() {
        // Force specific classes to load eagerly.
        EventTypeProvider.getInstance();
    }
}
