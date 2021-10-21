package com.konfyrm.songname.resource;

import com.konfyrm.songname.dto.CreateSongRequest;
import com.konfyrm.songname.dto.GetSongResponse;
import com.konfyrm.songname.dto.GetSongsResponse;
import com.konfyrm.songname.dto.UpdateSongRequest;
import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.model.Song;
import com.konfyrm.songname.service.AuthorsService;
import com.konfyrm.songname.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequestMapping("/api/songs")
@RestController
public class SongsResource {

    private final SongsService songsService;
    private final AuthorsService authorsService;

    @Autowired
    public SongsResource(
            SongsService songsService,
            AuthorsService authorsService
    ) {
        this.songsService = songsService;
        this.authorsService = authorsService;
    }

    @GetMapping
    public ResponseEntity<GetSongsResponse> getSongs() {
        List<Song> songs = songsService.getAllSongs();
        return ResponseEntity.ok(new GetSongsResponse(songs));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<GetSongResponse> getSong(@PathVariable("uuid") String uuid) {
        Optional<Song> song = songsService.getSongById(UUID.fromString(uuid));
        return song.map(value -> ResponseEntity.ok(new GetSongResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createSong(@RequestBody CreateSongRequest request, UriComponentsBuilder builder) {
        Optional<Author> author = authorsService.getAuthorById(request.getAuthorUuid());
        if (author.isPresent()) {
            Song song = new Song(request.getTitle(), author.get());
            return ResponseEntity.created(builder.pathSegment("api", "songs", "{uuid}")
                    .buildAndExpand(song.getUuid()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteSong(@PathVariable("uuid") String uuid) {
        Optional<Song> song = songsService.getSongById(UUID.fromString(uuid));
        if (song.isPresent()) {
            songsService.removeSongById(song.get().getUuid());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> updateSong(@RequestBody UpdateSongRequest request, @PathVariable("uuid") String uuid) {
        Optional<Song> song = songsService.getSongById(UUID.fromString(uuid));
        //todo: check if necessary
        Optional<Author> author = authorsService.getAuthorById(request.getAuthorUuid());
        if (song.isPresent() && author.isPresent()) {
            song.get().setAuthor(author.get());
            song.get().setTitle(request.getTitle());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
