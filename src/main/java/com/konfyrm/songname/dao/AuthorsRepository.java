package com.konfyrm.songname.dao;

import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.model.Song;
import com.konfyrm.songname.storage.FakeDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorsRepository extends CrudRepository<Author, UUID> {
/*
    @Query("select * from Author")
    List<Author> getAllAuthors();

    @Query("select a from Author where a.uuid = uuid")
    Author getAuthorById(@Param("uuid") UUID uuid);
    */
}
