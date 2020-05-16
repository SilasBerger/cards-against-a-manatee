package ch.silasberger.cardswebproto.dao;

import ch.silasberger.cardswebproto.model.WhiteCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhiteCardDao extends JpaRepository<WhiteCard, Integer> {
}
