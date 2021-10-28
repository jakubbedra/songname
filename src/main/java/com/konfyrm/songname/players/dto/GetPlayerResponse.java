package com.konfyrm.songname.players.dto;

import com.konfyrm.songname.players.model.Player;

import java.util.UUID;

public class GetPlayerResponse {

    private UUID uuid;
    private String name;
    private int score;

    public GetPlayerResponse(Player player) {
        this.uuid = player.getUuid();
        this.name = player.getName();
        this.score = player.getScore();
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

}
