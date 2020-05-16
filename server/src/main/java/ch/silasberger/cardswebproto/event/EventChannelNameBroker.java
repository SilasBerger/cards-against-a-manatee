package ch.silasberger.cardswebproto.event;

import ch.silasberger.cardswebproto.lobby.Lobby;
import ch.silasberger.cardswebproto.lobby.LobbyMember;
import ch.silasberger.cardswebproto.remoteplayer.RemotePlayer;

public class EventChannelNameBroker {

    public static String getChannelFor(RemotePlayer player) {
        return "player." + player.getId();
    }

    public static String getChannelFor(LobbyMember lobbyMember) {
        return "player." + lobbyMember.getMemberId();
    }

    public static String getChannelFor(Lobby lobby) {
        return "lobby." + lobby.getId();
    }

}
