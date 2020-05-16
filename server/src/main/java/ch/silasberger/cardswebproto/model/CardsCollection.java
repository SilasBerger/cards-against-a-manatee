package ch.silasberger.cardswebproto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CardsCollection {

    private final List<BlackCard> blackCards;
    private final List<WhiteCard> whiteCards;
    private int blackCardIndex;
    private int whiteCardIndex;

    public CardsCollection(List<BlackCard> blackCards, List<WhiteCard> whiteCards) {
        // TODO: Remove this filter later on, once we can deal with multi-picks.
        this.blackCards = blackCards.stream()
                .filter(blackCard -> blackCard.getPick() == 1)
                .collect(Collectors.toList());
        this.whiteCards = whiteCards;
        this.blackCardIndex = 0;
        this.whiteCardIndex = 0;
    }

    public WhiteCard nextWhite() {
        if (!hasNextWhite()) {
            return null;
        }
        WhiteCard next = whiteCards.get(whiteCardIndex);
        whiteCardIndex++;
        return next;
    }

    public List<WhiteCard> nextManyWhite(int count) {
        if (!hasNextManyWhite(count)) {
            return null;
        }
        List<WhiteCard> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add(nextWhite());
        }
        return result;
    }

    public BlackCard nextBlack() {
        if (!hasNextBlack()) {
            return null;
        }
        BlackCard next = blackCards.get(blackCardIndex);
        blackCardIndex++;
        return next;
    }

    public List<BlackCard> nextManyBlack(int count) {
        if (!hasNextManyBlack(count)) {
            return null;
        }
        List<BlackCard> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add(nextBlack());
        }
        return result;
    }

    public Optional<WhiteCard> findWhiteById(int id) {
        return whiteCards.stream()
                .filter(whiteCard -> whiteCard.getId() == id)
                .findFirst();
    }

    public Optional<BlackCard> findBlackById(int id) {
        return blackCards.stream()
                .filter(whiteCard -> whiteCard.getId() == id)
                .findFirst();
    }

    public boolean hasNextWhite() {
        return whiteCardIndex < whiteCards.size();
    }

    public boolean hasNextManyWhite(int count) {
        return (whiteCardIndex + count - 1) < whiteCards.size();
    }

    public boolean hasNextBlack() {
        return blackCardIndex < blackCards.size();
    }

    public boolean hasNextManyBlack(int count) {
        return (blackCardIndex + count - 1) < blackCards.size();
    }
}
