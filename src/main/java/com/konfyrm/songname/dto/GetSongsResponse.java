package com.konfyrm.songname.dto;

import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.model.Song;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class GetSongsResponse {

    public class SongGet {

        private UUID uuid;
        private String title;
        private String author;
        private String filePath;

        public SongGet(UUID uuid, String title, String author, String filePath) {
            this.uuid = uuid;
            this.title = title;
            this.author = author;
            this.filePath = filePath;
        }


        public UUID getUuid() {
            return uuid;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }

    private List<SongGet> songs;

    public GetSongsResponse(List<Song> songs) {
        this.songs = new LinkedList<>();
        songs.forEach(s ->
                this.songs.add(new SongGet(s.getUuid(), s.getTitle(), s.getAuthor().getName(), s.getFilePath()))
        );
    }

    public List<SongGet> getSongs() {
        return songs;
    }

}
