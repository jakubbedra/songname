package com.konfyrm.songname.storage;

import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.model.Song;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * Temporary class for the sake of first lab,
 * because we do not have an actual db yet.
 */
@Component
public class FakeDatabase {

    private List<Song> songsDb;
    private List<Author> authorsDb;

    public FakeDatabase() {
        this.songsDb = new LinkedList<>();
        this.authorsDb = new LinkedList<>();
    }

    /**
     * Hardcodes the data...
     */
    @PostConstruct
    private void fillStorage() {
        Author author1 = new Author("Alberto");
        Author author2 = new Author("Malik Montana");
        authorsDb.add(author1);
        authorsDb.add(author2);
        songsDb.add(new Song("Dwutakt", List.of(author1)));
        songsDb.add(new Song("Who you mam", List.of(author2)));
        songsDb.add(new Song("Ołów", List.of(author2, author1)));
    }

    public List<Author> getAllAuthors() {
        return authorsDb;
    }

    /*
        TODO: migrate String uuid to long id later on
     */
    public Author getAuthorById(String uuid) {
        return authorsDb.stream()
                .filter(author -> author.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No author with the id: " + uuid + " was found."));
    }

    public void addNewAuthor(Author author) {
        authorsDb.add(author);
    }

    public void removeAuthorById(String uuid) {
        Author original = getAuthorById(uuid);
        authorsDb.remove(original);             //does it work???
    }

    public List<Song> getAllSongs() {
        return songsDb;
    }

    /*
         TODO: as stated above, will probably be good to migrate the id
     */
    public Song getSongById(String uuid) {
        return songsDb.stream()
                .filter(song -> song.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No song with the id: " + uuid + " was found."));
    }

    public void addNewSong(Song song) {
        songsDb.add(song);
    }

    public void removeSongById(String uuid) {
        Song original = getSongById(uuid);
    }

}
