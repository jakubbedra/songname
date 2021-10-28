package com.konfyrm.songname.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konfyrm.songname.config.dto.CreateAuthorsFileDto;
import com.konfyrm.songname.config.dto.CreateSongsFileDto;
import com.konfyrm.songname.config.dto.GetAuthorsFileDto;
import com.konfyrm.songname.config.dto.GetSongsFileDto;
import com.konfyrm.songname.authors.model.Author;
import com.konfyrm.songname.songs.model.Song;
import com.konfyrm.songname.authors.service.AuthorsService;
import com.konfyrm.songname.songs.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Component
public class DataManager {

    private final SongsService songsService;
    private final AuthorsService authorsService;

    private final String jsonFilesPath;
    private static final String tmpPath = "./target/classes/json_data/"; //todo: make this shit work correctly xdddd

    @Autowired
    public DataManager(
            SongsService songsService,
            AuthorsService authorsService
    ) {
        this.songsService = songsService;
        this.authorsService = authorsService;
        URL res = getClass().getClassLoader().getResource("json_data/");
//        System.out.println(res.getPath());
        this.jsonFilesPath = res.getPath();
//        this.jsonFilesPath = tmpPath;
    }

    public void initData() {
        Author malik = new Author("Malik Montana");
        Author alberto = new Author("Alberto");
        authorsService.addAuthor(malik);
        authorsService.addAuthor(alberto);

        Song song1 = new Song("Mordo weź", malik);
        Song song2 = new Song("6.3 AMG", malik);
        Song song3 = new Song("Dwutakt", alberto);
        Song song4 = new Song("Strzał", alberto);
        songsService.addSong(song1);
        songsService.addSong(song2);
        songsService.addSong(song3);
        songsService.addSong(song4);
    }

    /*
    todo: make it pre-destroy and importing post construct
     */
    public void exportDataToFile() {
        List<Author> authors = authorsService.getAllAuthors();
        List<Song> songs = songsService.getAllSongs();
        CreateAuthorsFileDto authorsDto = new CreateAuthorsFileDto(authors);
        CreateSongsFileDto songsDto = new CreateSongsFileDto(songs);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(jsonFilesPath + "authors.json"), authorsDto);
            mapper.writeValue(new File(jsonFilesPath + "songs.json"), songsDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importDataFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            GetAuthorsFileDto authorsDto = mapper.readValue(
                    new File(tmpPath + "authors.json"), GetAuthorsFileDto.class
            );
            List<Author> authors = new LinkedList<>();
            Map<UUID, Author> authorsMap = new HashMap();
            authorsDto.getAuthors().forEach(a -> {
                Author author = new Author(a.getUuid(), a.getName());
                authors.add(author);
                authorsMap.put(a.getUuid(), author);
            });
            authors.forEach(authorsService::addAuthor);

            GetSongsFileDto songsDto = mapper.readValue(
                    new File(tmpPath + "songs.json"), GetSongsFileDto.class
            );
            List<Song> songs = new LinkedList<>();
            songsDto.getSongs().forEach(s ->
                    songs.add(new Song(s.getUuid(), s.getTitle(), authorsMap.get(s.getAuthorUuid())))
            );
            songs.forEach(songsService::addSong);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
