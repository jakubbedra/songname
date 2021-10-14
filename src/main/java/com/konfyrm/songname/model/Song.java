package com.konfyrm.songname.model;

import java.io.Serializable;
import java.util.UUID;

public class Song implements Serializable {

    private UUID uuid;
    private String title;
    /*
        TODO: Single author just for the sake of the lab, replace with N:N relationship later on
     */
    private Author author;
    private String filePath;

    public Song(String title, Author author) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.filePath = null; //tbi
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Song{ uuid: " + uuid + "; title: " + title + "; author: " + author.getName() + ";}";
    }

}
