package com.konfyrm.songname.dto;

import java.util.UUID;

public class CreatePlayerRequest {

    private UUID uuid;
    private String name;
    private int score;
    private UUID gameUuid;

    public CreatePlayerRequest(UUID uuid, String name, int score, UUID gameUuid) {
        this.uuid = uuid;
        this.name = name;
        this.score = score;
        this.gameUuid = gameUuid;
    }

    public UUID getUuid(){
        return uuid;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public UUID getGameUuid(){
        return gameUuid;
    }

    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setGameUuid(UUID gameUuid){
        this.gameUuid = gameUuid;
    }

}
