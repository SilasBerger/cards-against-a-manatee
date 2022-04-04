package ch.silasberger.cardswebproto.service;

import ch.silasberger.cardswebproto.model.BlackCard;
import ch.silasberger.cardswebproto.model.CardsCollection;
import ch.silasberger.cardswebproto.model.WhiteCard;
import ch.silasberger.cardswebproto.repository.CardsRepository;
import ch.silasberger.cardswebproto.util.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CardsService {

    private final CardsRepository cardsRepository;

    @Autowired
    public CardsService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public CardsCollection loadCardSet(String cardSetName) throws ApplicationException {
        List<BlackCard> blackCards = cardsRepository.loadBlackCardsForSet(cardSetName);
        List<WhiteCard> whiteCards = cardsRepository.loadWhiteCardsForSet(cardSetName);
        Collections.shuffle(blackCards);
        Collections.shuffle(whiteCards);

        return new CardsCollection(blackCards, whiteCards);
    }
}
