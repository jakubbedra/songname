package com.konfyrm.songname.resource;

import com.konfyrm.songname.dto.GetAuthorsResponse;
import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.service.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

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

    @GetMapping
    public ResponseEntity<GetAuthorsResponse> authors() {
        List<Author> all = authorsService.getAllAuthors();
        Function<Collection<Author>, GetAuthorsResponse> mapper = GetAuthorsResponse.entityToDtoMapper();
        GetAuthorsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

}
