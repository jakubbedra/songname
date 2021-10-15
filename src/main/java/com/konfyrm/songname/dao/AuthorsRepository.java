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
public class AuthorsRepository {

    private FakeDatabase database;

    @Autowired
    public AuthorsRepository(FakeDatabase database) {
        this.database = database;
    }

    public List<Author> getAllAuthors() {
        return database.getAuthors();
    }

    public Author getAuthorById(UUID uuid) {
        return database.getAuthorById(uuid);
    }

    public void addNewAuthor(Author author) {
        database.addNewAuthor(author);
    }

    /**
     * Removes the author with the given uuid along with all of his songs
     * (does not apply to featurings on other authors' songs).
     *
     * @param uuid A {@code UUID} of the author.
     */
    public void removeAuthorById(UUID uuid) {
        List<Song> authorsSongs = database.getSongs().stream()
                .filter(s -> s.getAuthor().getUuid().equals(uuid))
                .collect(Collectors.toList());
        authorsSongs.stream().forEach(s -> database.removeSongById(s.getUuid()));
        database.removeAuthorById(uuid);
    }

}
