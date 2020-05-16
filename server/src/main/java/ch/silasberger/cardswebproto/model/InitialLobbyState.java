package ch.silasberger.cardswebproto.model;

import java.util.List;
import java.util.UUID;

public class InitialLobbyState {

    private String lobbyId;
    private List<LobbyMemberRepresentation> members;
    private UUID leaderId;
    private boolean gameActive;

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public List<LobbyMemberRepresentation> getMembers() {
        return members;
    }

    public void setMembers(List<LobbyMemberRepresentation> members) {
        this.members = members;
    }

    public UUID getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(UUID leaderId) {
        this.leaderId = leaderId;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
    }
}
