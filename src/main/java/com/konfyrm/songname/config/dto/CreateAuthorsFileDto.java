package com.konfyrm.songname.config.dto;

import com.konfyrm.songname.model.Author;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class CreateAuthorsFileDto {

    public class AuthorGet {

        private UUID uuid;
        private String name;

        public AuthorGet(UUID uuid, String name) {
            this.uuid = uuid;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public UUID getUuid() {
            return uuid;
        }

        public void setUuid(UUID uuid) {
            this.uuid = uuid;
        }

    }

    private List<AuthorGet> authors;

    public CreateAuthorsFileDto(List<Author> authors) {
        this.authors = new LinkedList<>();
        authors.forEach(a ->
                this.authors.add(new AuthorGet(a.getUuid(), a.getName()))
        );
    }

    public List<AuthorGet> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorGet> authors) {
        this.authors = authors;
    }

}
