package com.konfyrm.songname.players.dto;

import java.util.UUID;

public class CreatePlayerRequest {

    private String name;
    private UUID gameUuid;

    public CreatePlayerRequest(String name, UUID gameUuid) {
        this.name = name;
        this.gameUuid = gameUuid;
    }

    public String getName(){
        return name;
    }

    public UUID getGameUuid(){
        return gameUuid;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGameUuid(UUID gameUuid){
        this.gameUuid = gameUuid;
    }

}
