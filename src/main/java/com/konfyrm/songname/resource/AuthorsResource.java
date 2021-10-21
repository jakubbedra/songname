package com.konfyrm.songname.resource;

import com.konfyrm.songname.dto.UpdateAuthorRequest;
import com.konfyrm.songname.dto.CreateAuthorRequest;
import com.konfyrm.songname.dto.GetAuthorResponse;
import com.konfyrm.songname.dto.GetAuthorsResponse;
import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.service.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/authors")
public class AuthorsResource {

    private final AuthorsService authorsService;

    @Autowired
    public AuthorsResource(
            AuthorsService authorsService
    ) {
        this.authorsService = authorsService;
    }

    /**
     * Returns the list of all authors stored in database.
     *
     * @return A {@code GetAuthorsResponse} containing the list of authors.
     */
    @GetMapping
    public ResponseEntity<GetAuthorsResponse> getAuthors() {
        List<Author> all = authorsService.getAllAuthors();
        return ResponseEntity.ok(new GetAuthorsResponse(all));
    }

    /**
     * Returns the author with a specific uuid.
     *
     * @param uuid A {@code UUID} of the author.
     * @return
     */
    @GetMapping("{uuid}")
    public ResponseEntity<GetAuthorResponse> getAuthor(@PathVariable("uuid") UUID uuid) {
        Optional<Author> author = authorsService.getAuthorById(uuid);
        return author.map(value -> ResponseEntity.ok(new GetAuthorResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createAuthor(@RequestBody CreateAuthorRequest request, UriComponentsBuilder builder) {
        Author author = new Author(request.getName());
        authorsService.addNewAuthor(author);
        return ResponseEntity.created(builder.pathSegment("api", "authors", "{uuid}")
                .buildAndExpand(author.getUuid()).toUri()).build();
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("uuid") UUID uuid) {
        Optional<Author> author = authorsService.getAuthorById(uuid);
        if (author.isPresent()) {
            authorsService.removeAuthorById(author.get().getUuid());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{uuid}")
    public ResponseEntity<Void> updateAuthor(@RequestBody UpdateAuthorRequest request, @PathVariable("uuid") UUID uuid) {
        Optional<Author> author = authorsService.getAuthorById(uuid);
        if (author.isPresent()) {
            author.get().setName(request.getName());
            authorsService.updateAuthor(author.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}