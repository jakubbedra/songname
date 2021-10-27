package com.konfyrm.songname.service;

import com.konfyrm.songname.dao.AuthorsRepository;
import com.konfyrm.songname.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AuthorsService {

    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsService(
            AuthorsRepository authorsRepository
    ) {
        this.authorsRepository = authorsRepository;
    }

    public List<Author> getAllAuthors() {
        return new LinkedList<>((Collection<Author>) authorsRepository.findAll());
    }

    public Optional<Author> getAuthorById(UUID uuid) {
        return authorsRepository.findById(uuid);
    }

    @Transactional
    public void addAuthor(Author author) {
        authorsRepository.save(author);
    }

    /**
     * Removes the author with the given uuid along with all of his songs
     * (does not apply to featurings on other authors' songs).
     *
     * @param uuid A {@code UUID} of the author.
     */
    @Transactional
    public void removeAuthorById(UUID uuid) {
        authorsRepository.deleteById(uuid);
    }

}
