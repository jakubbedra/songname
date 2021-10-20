package com.konfyrm.songname.dto;

import com.konfyrm.songname.model.Author;

import java.util.Collection;
import java.util.UUID;
import java.util.List;
import java.util.function.Function;

public class GetAuthorsResponse {

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

    public static Function<Collection<Author>, GetAuthorsResponse> entityToDtoMapper() {

    }

}
