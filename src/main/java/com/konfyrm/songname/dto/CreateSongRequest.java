package com.konfyrm.songname.dto;

import java.util.UUID;

public class CreateSongRequest {

    private String title;
    private UUID authorUuid;

    public CreateSongRequest(String title, UUID authorUuid) {
        this.title = title;
        this.authorUuid = authorUuid;
    }

    public String getTitle() {
        return title;
    }

    public UUID getAuthorUuid() {
        return authorUuid;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthorUuid(UUID authorUuid){
        this.authorUuid = authorUuid;
    }

}
