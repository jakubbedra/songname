package com.konfyrm.songname.labtrash;

import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.model.Song;
import com.konfyrm.songname.storage.FakeDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class DataInitializer {

    private final FakeDatabase database;

    @Autowired
    public DataInitializer(
            FakeDatabase database
    ) {
        this.database = database;
    }

    /**
     * Hardcodes sample data into the fake database
     */
    @PostConstruct
    public void initData() {
        Author author1 = new Author("Alberto");
        Author author2 = new Author("Malik Montana");
        database.addNewAuthor(author1);
        database.addNewAuthor(author2);
        database.addNewSong(new Song("Dwutakt", author1));
        database.addNewSong(new Song("Strzał", author1));
        database.addNewSong(new Song("Who you mam", author2));
        database.addNewSong(new Song("6.3 AMG", author2));
    }

}
