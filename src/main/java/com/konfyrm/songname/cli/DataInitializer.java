package com.konfyrm.songname.cli;

import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.model.Song;
import com.konfyrm.songname.service.AuthorsService;
import com.konfyrm.songname.service.SongsService;
import com.konfyrm.songname.storage.FakeDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final FakeDatabase database;
    private final SongsService songsService;
    private final AuthorsService authorsService;

    @Autowired
    public DataInitializer(
            FakeDatabase database,
            SongsService songsService,
            AuthorsService authorsService
    ) {
        this.database = database;
        this.songsService = songsService;
        this.authorsService = authorsService;
    }

    public void initData() {
        Author malik = new Author("Malik Montana");
        Author alberto = new Author("Alberto");
        authorsService.addNewAuthor(malik);
        authorsService.addNewAuthor(alberto);

        Song song1 = new Song("Who you mam", malik);
        Song song2 = new Song("6.3 AMG", malik);
        Song song3 = new Song("Dwutakt", alberto);
        Song song4 = new Song("Strzał", alberto);
        songsService.addNewSong(song1);
        songsService.addNewSong(song2);
        songsService.addNewSong(song3);
        songsService.addNewSong(song4);
    }

}
