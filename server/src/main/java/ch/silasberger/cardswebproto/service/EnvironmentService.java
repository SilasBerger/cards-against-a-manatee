package ch.silasberger.cardswebproto.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class EnvironmentService {

    private static final String VAR_CARD_PACKS_BASE_PATH = "CARD_PACKS_BASE_PATH";

    public Optional<Path> getCardPacksBasePath() {
        String cardPacksBasePath = System.getenv(VAR_CARD_PACKS_BASE_PATH);
        if (cardPacksBasePath == null) {
            return Optional.empty();
        }
        return Optional.of(new File(cardPacksBasePath).toPath());
    }
}
