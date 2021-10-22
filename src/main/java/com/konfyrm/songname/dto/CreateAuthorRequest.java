package com.konfyrm.songname.dto;

import com.konfyrm.songname.model.Author;

import java.util.UUID;

public class CreateAuthorRequest {

    private String name;

    public CreateAuthorRequest() {
    }

    public CreateAuthorRequest(Author author) {
        this.name = author.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
