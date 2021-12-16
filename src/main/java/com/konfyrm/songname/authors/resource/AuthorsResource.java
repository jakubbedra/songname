package com.konfyrm.songname.authors.resource;

import com.konfyrm.songname.authors.dto.*;
import com.konfyrm.songname.authors.model.Author;
import com.konfyrm.songname.songs.dto.GetSongsResponse;
import com.konfyrm.songname.songs.model.Song;
import com.konfyrm.songname.authors.service.AuthorsService;
import com.konfyrm.songname.songs.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequestMapping("/api/authors")
@RestController
public class AuthorsResource {

    private final AuthorsService authorsService;
    private final SongsService songsService;

    @Autowired
    public AuthorsResource(
            AuthorsService authorsService,
            SongsService songsService
    ) {
        this.authorsService = authorsService;
        this.songsService = songsService;
    }

    @GetMapping
    public ResponseEntity<GetAuthorsResponse> getAuthors() {
        List<Author> all = authorsService.getAllAuthors();
        return ResponseEntity.ok(new GetAuthorsResponse(all));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<GetAuthorResponse> getAuthor(@PathVariable("uuid") String uuid) {
        Optional<Author> author = authorsService.getAuthorById(UUID.fromString(uuid));
        return author.map(value -> ResponseEntity.ok(new GetAuthorResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{uuid}/songs")
    public ResponseEntity<GetSongsResponse> getAuthorSongs(@PathVariable("uuid") String uuid) {
        List<Song> songs = songsService.getAuthorSongs(UUID.fromString(uuid));
        return ResponseEntity.ok(new GetSongsResponse(songs));
    }

    @PostMapping
    public ResponseEntity<Void> createAuthor(@RequestBody CreateAuthorRequest request, UriComponentsBuilder builder) {
        Author author = new Author(request.getName());
        authorsService.addAuthor(author);
        return ResponseEntity.created(builder.pathSegment("api", "authors", "{uuid}")
                .buildAndExpand(author.getUuid()).toUri()).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("uuid") String uuid) {
        Optional<Author> author = authorsService.getAuthorById(UUID.fromString(uuid));
        if (author.isPresent()) {
            authorsService.removeAuthorById(author.get().getUuid());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> updateAuthor(@RequestBody UpdateAuthorRequest request, @PathVariable("uuid") String uuid) {
        Optional<Author> author = authorsService.getAuthorById(UUID.fromString(uuid));
        if (author.isPresent()) {
            author.get().setName(request.getName());
            authorsService.addAuthor(author.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}