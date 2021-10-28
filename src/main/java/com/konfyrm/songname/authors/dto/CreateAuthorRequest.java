package com.konfyrm.songname.authors.dto;

import com.konfyrm.songname.authors.model.Author;

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
