package com.konfyrm.songname.players.dto;

public class UpdatePlayerScore {

    private int points;

    public UpdatePlayerScore() {

    }

    public UpdatePlayerScore(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
