package com.konfyrm.songname.service;

import com.konfyrm.songname.dao.SongsRepository;
import com.konfyrm.songname.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SongsService {

    private final SongsRepository songsRepository;

    @Autowired
    public SongsService(
            SongsRepository songsRepository
    ){
        this.songsRepository = songsRepository;
    }

    public List<Song> getAllSongs() {
        return songsRepository.getAllSongs();
    }

    public Song getSongById(UUID uuid) {
        return songsRepository.getSongById(uuid);
    }

    public void addNewSong(Song song) {
        songsRepository.addNewSong(song);
    }

    public void removeSongById(UUID uuid) {
        songsRepository.removeSongById(uuid);
    }

}
