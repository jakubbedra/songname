package com.konfyrm.songname.songs.dto;

import com.konfyrm.songname.songs.model.Song;

import java.util.UUID;

public class GetSongResponse {

    private UUID uuid;
    private String title;
    private String author;

    public GetSongResponse(Song song) {
        this.uuid = song.getUuid();
        this.title = song.getTitle();
        this.author = song.getAuthor().getName();
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
