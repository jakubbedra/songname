package com.konfyrm.songname.players.repository;

import com.konfyrm.songname.players.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayersRepository extends CrudRepository<Player, UUID> {

}
