package com.konfyrm.songname.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author implements Serializable {

    @Id
    private UUID uuid;

    @Column(name = "author_name")
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "author")
    private List<Song> songs;

    public Author() {
    }

    public Author(UUID uuid, String name, List<Song> songs) {
        this.uuid = uuid;
        this.name = name;
        this.songs = songs;
    }

    public Author(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public Author(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
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
        if (o == this) {
            return true;
        } else if (!(o instanceof Author)) {
            return false;
        } else {
            Author other = (Author) o;
            return other.name.equals(this.name) && other.uuid.equals(this.uuid);
        }
    }

}
