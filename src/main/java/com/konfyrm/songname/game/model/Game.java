package com.konfyrm.songname.game.model;

import java.util.List;
import java.util.UUID;

/**
 * Class containing the needed game data.
 */
public class Game {

    private List<UUID> players;
    private List<UUID> songs;

    private int turns;
    private int currentTurn;

    public Game(List<UUID> players, List<UUID> songs, int turns) {
        this.players = players;
        this.songs = songs;
        this.turns = turns;
        this.currentTurn = 0;
    }

    public UUID getCurrentSong() {
        return songs.get(currentTurn);
    }

    public UUID getCurrentPlayer() {
        return players.get(currentTurn % players.size());
    }

    public boolean nextTurn() {
        currentTurn++;
        return currentTurn < turns;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public int getTurns() {
        return turns;
    }

}
