package com.konfyrm.songname.dao;

import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.storage.FakeDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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

    public void removeAuthorById(UUID uuid) {
        database.removeAuthorById(uuid);
    }

}
