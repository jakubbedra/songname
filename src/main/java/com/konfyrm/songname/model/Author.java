package com.konfyrm.songname.model;

import java.io.Serializable;
import java.util.UUID;

public class Author implements Serializable {

    private String uuid;
    private String name;

    public Author(String name) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getAuthor() {
        return name;
    }

}
