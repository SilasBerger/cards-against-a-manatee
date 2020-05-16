package ch.silasberger.cardswebproto.service;

import ch.silasberger.cardswebproto.dao.WhiteCardDao;
import ch.silasberger.cardswebproto.model.WhiteCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WhiteCardService {

    private WhiteCardDao whiteCardDao;

    @Autowired
    public WhiteCardService(WhiteCardDao whiteCardDao) {
        this.whiteCardDao = whiteCardDao;
    }

    public List<WhiteCard> getAll() {
        return whiteCardDao.findAll();
    }

    public Optional<WhiteCard> getById(int id) {
        return whiteCardDao.findById(id);
    }

    public List<Integer> getIds() {
        return getAll()
                .stream()
                .map(WhiteCard::getId)
                .collect(Collectors.toList());
    }
}
