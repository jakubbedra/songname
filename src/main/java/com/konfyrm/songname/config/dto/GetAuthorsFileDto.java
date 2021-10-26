package com.konfyrm.songname.config.dto;

import java.util.List;
import java.util.UUID;

public class GetAuthorsFileDto {

    public static class AuthorGet {

        private UUID uuid;
        private String name;

        public AuthorGet() {
        }

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

    public GetAuthorsFileDto() {
    }

    public List<AuthorGet> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorGet> authors) {
        this.authors = authors;
    }

}
