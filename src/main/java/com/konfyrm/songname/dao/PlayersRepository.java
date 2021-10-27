package com.konfyrm.songname.dao;

import com.konfyrm.songname.model.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlayersRepository extends CrudRepository<Player, UUID> {

}
