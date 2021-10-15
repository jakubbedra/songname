package com.konfyrm.songname.dao;

import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.model.Song;
import com.konfyrm.songname.storage.FakeDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SongsRepository {

    private FakeDatabase database;

    @Autowired
    public SongsRepository(FakeDatabase database) {
        this.database = database;
    }

    public List<Song> getAllSongs() {
        return database.getSongs();
    }

    public Song getSongById(UUID uuid) {
        return database.getSongById(uuid);
    }

    public void addNewSong(Song song) {
        database.addNewSong(song);
    }

    public void removeSongById(UUID uuid) {
        database.removeSongById(uuid);
    }

    /**
     * Removes all of the author's songs.
     *
     * @param author An {@code Author} of which we want the songs to remove.
     */
    public void removeSongsByAuthor(Author author) {
        List<Song> authorsSongs = database.getSongs().stream()
                .filter( s -> s.getAuthor().equals(author))
                .collect(Collectors.toList());
        authorsSongs.stream().forEach(s -> database.removeSongById(s.getUuid()));
    }

}
