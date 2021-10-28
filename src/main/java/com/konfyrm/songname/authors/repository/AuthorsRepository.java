package com.konfyrm.songname.authors.repository;

import com.konfyrm.songname.authors.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorsRepository extends CrudRepository<Author, UUID> {

}
