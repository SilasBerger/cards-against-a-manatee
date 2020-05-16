package ch.silasberger.cardswebproto.lobby;

import java.util.UUID;

public interface LobbyMember {
    UUID getMemberId();
    String getMemberName();
}
