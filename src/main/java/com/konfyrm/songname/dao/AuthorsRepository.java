package com.konfyrm.songname.dao;

import com.konfyrm.songname.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorsRepository extends CrudRepository<Author, UUID> {

}
