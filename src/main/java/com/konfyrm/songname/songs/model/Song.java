package com.konfyrm.songname.songs.model;

import com.konfyrm.songname.authors.model.Author;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "songs")
public class Song implements Serializable {

    @Id
    private UUID uuid;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    public Song() {
    }

    public Song(String title, Author author) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.author = author;
    }

    public Song(UUID uuid, String title, Author author) {
        this.uuid = uuid;
        this.title = title;
        this.author = author;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Song{ uuid: " + uuid + "; title: " + title + "; author: " + author.getName() + ";}";
    }

}
