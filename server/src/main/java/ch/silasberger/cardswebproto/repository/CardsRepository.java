package ch.silasberger.cardswebproto.repository;

import ch.silasberger.cardswebproto.model.BlackCard;
import ch.silasberger.cardswebproto.model.CardsCollection;
import ch.silasberger.cardswebproto.model.WhiteCard;
import ch.silasberger.cardswebproto.service.CardsCsvImportService;
import ch.silasberger.cardswebproto.service.EnvironmentService;
import ch.silasberger.cardswebproto.util.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CardsRepository {

    private static final String BLACK_CARDS_FILENAME = "black_cards.csv";
    private static final String WHITE_CARDS_FILENAME = "white_cards.csv";

    private final CardsCsvImportService cardsCsvImportService;
    private final EnvironmentService environmentService;

    @Autowired
    public CardsRepository(CardsCsvImportService cardsCsvImportService, EnvironmentService environmentService) {
        this.cardsCsvImportService = cardsCsvImportService;
        this.environmentService = environmentService;
    }

    public List<BlackCard> loadBlackCardsForSet(String setName) throws ApplicationException {
        File cardsFile = getPathToCardSet(setName).resolve(BLACK_CARDS_FILENAME).toFile();

        return cardsCsvImportService.importBlackCardsCsv(cardsFile).stream()
                .map(csv -> new BlackCard(csv.getId(), csv.getValue(), csv.getPick()))
                .collect(Collectors.toList());
    }

    public List<WhiteCard> loadWhiteCardsForSet(String setName) throws ApplicationException {
        File cardsFile = getPathToCardSet(setName).resolve(WHITE_CARDS_FILENAME).toFile();

        return cardsCsvImportService.importWhiteCardsCsv(cardsFile).stream()
                .map(csv -> new WhiteCard(csv.getId(), csv.getValue()))
                .collect(Collectors.toList());
    }

    private Path getPathToCardSet(String setName) throws ApplicationException {
        Path cardSetsBaseDir = environmentService
                .getCardPacksBasePath()
                .orElseThrow(() -> new ApplicationException("Card packs base path not set"));
        return cardSetsBaseDir.resolve(setName);
    }
}
