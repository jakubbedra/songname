package com.konfyrm.songname.dto;

import com.konfyrm.songname.model.Author;

import java.util.UUID;

public class UpdateAuthorRequest {

    private String name;

    public UpdateAuthorRequest(Author author) {
        this.name = author.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
