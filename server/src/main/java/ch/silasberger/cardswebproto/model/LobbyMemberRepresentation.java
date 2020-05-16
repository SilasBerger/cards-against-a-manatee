package ch.silasberger.cardswebproto.model;

import ch.silasberger.cardswebproto.lobby.LobbyMember;

import java.util.UUID;

public class LobbyMemberRepresentation {

    private UUID id;
    private String name;
    private int score;

    public LobbyMemberRepresentation() {
    }

    public static LobbyMemberRepresentation from(LobbyMember member) {
        // TODO: Update this once we have scores.
        return new LobbyMemberRepresentation(member.getMemberId(), member.getMemberName(), 0);
    }

    public LobbyMemberRepresentation(UUID id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
