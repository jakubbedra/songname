package com.konfyrm.songname.dto;

import com.konfyrm.songname.model.Song;

import java.util.UUID;

public class GetSongResponse {

    private UUID uuid;
    private String title;
    private String author;
    private String filePath;

    public GetSongResponse(Song song) {
        this.uuid = song.getUuid();
        this.title = song.getTitle();
        this.author = song.getAuthor().getName();
        this.filePath = song.getFilePath();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
