package ch.silasberger.cardswebproto.dao;

import ch.silasberger.cardswebproto.model.BlackCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackCardDao extends JpaRepository<BlackCard, Integer> {

}
