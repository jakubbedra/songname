package com.konfyrm.songname.game.manager;

import com.konfyrm.songname.game.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameManager {

    private Map<UUID, Game> games;

    @Autowired
    public GameManager() {
        this.games = new HashMap<>();
    }

    public Game createNewGame(List<UUID> players, List<UUID> songs, int turns) {
        return new Game(players, songs, turns);
    }

    public synchronized Optional<Game> getGame(UUID uuid) {
        return Optional.ofNullable(games.get(uuid));
    }

    public synchronized void addGame(UUID uuid, Game game) {
        games.put(uuid, game);
    }

    public synchronized void removeGame(UUID uuid) {
        games.remove(uuid);
    }

    public synchronized void updateGame(UUID uuid, Game game) {
        games.put(uuid, game);
    }

}
