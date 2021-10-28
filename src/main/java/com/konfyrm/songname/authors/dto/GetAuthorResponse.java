package com.konfyrm.songname.authors.dto;

import com.konfyrm.songname.authors.model.Author;

import java.util.UUID;

public class GetAuthorResponse {

    private UUID uuid;
    private String name;

    public GetAuthorResponse(Author author) {
        this.uuid = author.getUuid();
        this.name = author.getName();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

}
