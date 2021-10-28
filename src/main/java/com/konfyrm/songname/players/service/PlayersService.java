package com.konfyrm.songname.players.service;

import com.konfyrm.songname.players.repository.PlayersRepository;
import com.konfyrm.songname.players.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayersService {

    private final PlayersRepository playersRepository;

    @Autowired
    public PlayersService(
            PlayersRepository playersRepository
    ) {
        this.playersRepository = playersRepository;
    }

    public List<Player> getAllPlayers() {
        return new LinkedList<>((Collection<Player>) playersRepository.findAll());
    }

    public Optional<Player> getPlayerByID(UUID uuid) {
        return playersRepository.findById(uuid);
    }

    @Transactional
    public void addPlayer(Player player) {
        playersRepository.save(player);
    }

    @Transactional
    public void removePlayerByID(UUID uuid) {
        playersRepository.deleteById(uuid);
    }

    @Transactional
    public void updatePlayer(Player player) {
        playersRepository.save(player);
    }

    /**
     * Returns the list of all players that take part in the game with given uuid.
     *
     * @param uuid The {@code UUID} of the game.
     */
    public List<Player> getPlayersByGameId(UUID uuid) {
        List<Player> players = new LinkedList<>((Collection<Player>) playersRepository.findAll());
        return players.stream()
                .filter(p -> p.getGameUuid().equals(uuid))
                .collect(Collectors.toList());
    }

}
