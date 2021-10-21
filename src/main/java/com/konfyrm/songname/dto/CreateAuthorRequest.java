package com.konfyrm.songname.dto;

import com.konfyrm.songname.model.Author;

import java.util.UUID;

public class CreateAuthorRequest {

    private UUID uuid;
    private String name;

    public CreateAuthorRequest(Author author) {
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
