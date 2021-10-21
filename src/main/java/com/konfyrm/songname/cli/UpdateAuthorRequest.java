package com.konfyrm.songname.cli;

import com.konfyrm.songname.model.Author;

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
