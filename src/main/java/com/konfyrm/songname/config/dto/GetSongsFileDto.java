package com.konfyrm.songname.config.dto;

import java.util.List;
import java.util.UUID;

public class GetSongsFileDto {

    public static class SongGet {

        private UUID uuid;
        private String title;
        private UUID authorUuid;

        public SongGet() {
        }

        public SongGet(UUID uuid, String title, UUID authorUuid) {
            this.uuid = uuid;
            this.title = title;
            this.authorUuid = authorUuid;
        }

        public UUID getUuid() {
            return uuid;
        }

        public String getTitle() {
            return title;
        }

        public UUID getAuthorUuid() {
            return authorUuid;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAuthorUuid(UUID authorUuid) {
            this.authorUuid = authorUuid;
        }
    }

    private List<SongGet> songs;

    public GetSongsFileDto() {
    }

    public List<SongGet> getSongs() {
        return songs;
    }

    public void setSongs(List<SongGet> songs) {
        this.songs = songs;
    }

}
