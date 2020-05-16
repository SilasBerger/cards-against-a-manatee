package ch.silasberger.cardswebproto.service;

import ch.silasberger.cardswebproto.dao.BlackCardDao;
import ch.silasberger.cardswebproto.model.BlackCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlackCardService {

    private BlackCardDao blackCardDao;

    @Autowired
    public BlackCardService(BlackCardDao blackCardDao) {
        this.blackCardDao = blackCardDao;
    }

    public List<BlackCard> getAll() {
        return blackCardDao.findAll();
    }

    public Optional<BlackCard> getById(int id) {
        return blackCardDao.findById(id);
    }

    public List<Integer> getIds() {
        return getAll()
                .stream()
                .map(BlackCard::getId)
                .collect(Collectors.toList());
    }
}
