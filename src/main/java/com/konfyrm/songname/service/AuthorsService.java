package com.konfyrm.songname.service;

import com.konfyrm.songname.dao.AuthorsRepository;
import com.konfyrm.songname.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addNewAuthor(Author author) {
        authorsRepository.save(author);
    }

    /**
     * Removes the author with the given uuid along with all of his songs
     * (does not apply to featurings on other authors' songs).
     *
     * @param uuid A {@code UUID} of the author.
     */
    public void removeAuthorById(UUID uuid) {
        authorsRepository.deleteById(uuid);
    }

    /**
     * Updates the author in database.
     *
     * @param author An {@code Author} business entity.
     */
    public void updateAuthor(Author author) {
        authorsRepository.deleteById(author.getUuid());
        authorsRepository.save(author);
    }

}
