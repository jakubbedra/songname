package com.konfyrm.songname.labtrash;

import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.model.Song;
import com.konfyrm.songname.storage.FakeDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final FakeDatabase database;

    @Autowired
    public DataInitializer(
            FakeDatabase database
    ) {
        this.database = database;
        System.out.println("\ndupa\n");
    }

    public void initData() {
        Author malik = new Author("Malik Montana");
        Author alberto = new Author("Alberto");
        database.addNewAuthor(malik);
        database.addNewAuthor(alberto);
        Song song1 = new Song("Who you mam", malik);
        Song song2 = new Song("6.3 AMG", malik);
        Song song3 = new Song("Dwutakt", alberto);
        Song song4 = new Song("Strzał", alberto);
        database.addNewSong(song1);
        database.addNewSong(song2);
        database.addNewSong(song3);
        database.addNewSong(song4);
    }

}
