package com.konfyrm.songname.players.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "players")
public class Player implements Serializable {

    @Id
    private UUID uuid;
    private String name;
    private int score;
    private UUID gameUuid; //uuid for the game session todo: maybe remove dunno, its already contained in Game

    public Player() {
    }

    public Player(String name, UUID gameUuid) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.score = 0;
        this.gameUuid = gameUuid;
    }

    public Player(UUID uuid, String name, int score, UUID gameUuid) {
        this.uuid = uuid;
        this.name = name;
        this.score = score;
        this.gameUuid = gameUuid;
    }

    public void increaseScore(int points) {
        this.score += points;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public UUID getGameUuid() {
        return gameUuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGameUuid(UUID gameUuid) {
        this.gameUuid = gameUuid;
    }

}
