package com.konfyrm.songname.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Song implements Serializable {

    private String uuid;
    private String title;
    private List<Author> authors;
    private byte[] file; //might migrate byte[] to another type

    public Song(String title, List<Author> authors) {
        this.uuid = UUID.randomUUID().toString();
        this.title = title;
        this.authors = authors;
        this.file = null; //tbi
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public byte[] getFile() {
        return file;
    }

}
