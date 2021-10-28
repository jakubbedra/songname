package com.konfyrm.songname.songs.repository;

import com.konfyrm.songname.songs.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SongsRepository extends CrudRepository<Song, UUID> {

}
