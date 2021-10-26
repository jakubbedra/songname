package com.konfyrm.songname.dto;

public class UpdateAuthorRequest {

    private String name;

    public UpdateAuthorRequest() {
    }

    public UpdateAuthorRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
