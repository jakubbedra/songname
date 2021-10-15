package com.konfyrm.songname.storage;

import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.model.Song;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Temporary class for the sake of first lab,
 * because we do not have an actual db yet.
 */
@Component
public class FakeDatabase {

    private List<Song> songs;
    private List<Author> authors;

    public FakeDatabase() {
        this.songs = new LinkedList<>();
        this.authors = new LinkedList<>();
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public Author getAuthorById(UUID uuid) {
        return authors.stream()
                .filter(author -> author.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No author with the id: " + uuid + " was found."));
    }

    public void addNewAuthor(Author author) {
        authors.add(author);
    }

    public void removeAuthorById(UUID uuid) {
        Author original = getAuthorById(uuid);
        authors.remove(original);
    }

    public Song getSongById(UUID uuid) {
        return songs.stream()
                .filter(song -> song.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No song with the id: " + uuid + " was found."));
    }

    public void addNewSong(Song song) {
        songs.add(song);
    }

    public void removeSongById(UUID uuid) {
        Song original = getSongById(uuid);
        songs.remove(original);
    }

}
