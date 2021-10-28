package com.konfyrm.songname.songs.dto;

import java.util.UUID;

public class CheckSongRequest {

    private UUID uuid;
    private String title;
    private String authorName;

    public CheckSongRequest(UUID uuid, String title, String authorName) {
        this.uuid = uuid;
        this.title = title;
        this.authorName = authorName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

}
