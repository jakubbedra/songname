package com.konfyrm.songname.service;

import com.konfyrm.songname.dao.AuthorsRepository;
import com.konfyrm.songname.model.Author;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class AuthorsService {

    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsService(
            AuthorsRepository authorsRepository
    ) {
        this.authorsRepository = authorsRepository;
    }

    public List<Author> getAllAuthors() {
        return authorsRepository.getAllAuthors();
    }

    public Author getAuthorById(UUID uuid) {
        return authorsRepository.getAuthorById(uuid);
    }

    public void addNewAuthor(Author author) {
        authorsRepository.addNewAuthor(author);
    }

    public void removeAuthorById(UUID uuid) {
        authorsRepository.removeAuthorById(uuid);
    }

}