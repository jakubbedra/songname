package com.konfyrm.songname.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

public class Author implements Serializable {

    private UUID uuid;
    private String name;
    private List<Song> songs;

    public Author(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Author{ uuid: " + uuid + "; name: " + name + "; }";
    }

    @Override
    public boolean equals(Object o) {
        if(o == this){
            return true;
        } else if(!(o instanceof Author)){
            return false;
        } else {
            Author other = (Author)o;
            return other.name.equals(this.name) && other.uuid.equals(this.uuid);
        }
    }

}
