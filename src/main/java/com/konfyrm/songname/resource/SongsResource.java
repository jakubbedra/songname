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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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

    @GetMapping("/random")
    public ResponseEntity<GetSongResponse> getRandomSong() {
        List<Song> songs = songsService.getAllSongs();
        Random r = new Random();
        return ResponseEntity.ok(
                new GetSongResponse(songs.get(r.nextInt(songs.size())))
        );
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createSong(@RequestPart("file") MultipartFile file,
                                           @RequestPart("info") CreateSongRequest request,
                                           UriComponentsBuilder builder) throws IOException {
        Optional<Author> author = authorsService.getAuthorById(request.getAuthorUuid());
        if (author.isPresent()) {
            Song song = new Song(request.getTitle(), author.get());
            songsService.addSong(song);
            songsService.uploadFile(song.getUuid(), file.getInputStream());
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
        Optional<Author> author = authorsService.getAuthorById(request.getAuthorUuid());
        if (song.isPresent() && author.isPresent()) {
            song.get().setAuthor(author.get());
            song.get().setTitle(request.getTitle());
            songsService.updateSong(song.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{uuid}/file")
    public ResponseEntity<byte[]> getSongFile(@PathVariable("uuid") String uuid) throws IOException {
        Optional<Song> song = songsService.getSongById(UUID.fromString(uuid));
        if (song.isPresent()) {
            InputStream is = songsService.getFileById(UUID.fromString(uuid));
            byte[] bytes = is.readAllBytes();
            //InputStreamResource resource = new InputStreamResource(is);
            HttpHeaders headers = createFileHeaders(song.get().getTitle() + ".mp3");
            return ResponseEntity.ok().headers(headers)
                    .contentLength(bytes.length)
                    .contentType(MediaType.parseMediaType("application/mp3"))
                    .body(bytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/{uuid}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadSongFile(@RequestParam("songFile") MultipartFile file,
                                               @PathVariable("uuid") String uuid) throws IOException {
        System.out.println(file.getName());
        Optional<Song> song = songsService.getSongById(UUID.fromString(uuid));
        if (song.isPresent()) {
            songsService.uploadFile(song.get().getUuid(), file.getInputStream());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{uuid}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateSongFile(@RequestParam("songFile") MultipartFile file,
                                               @PathVariable("uuid") String uuid) throws IOException {
        Optional<Song> song = songsService.getSongById(UUID.fromString(uuid));
        if (song.isPresent()) {
            songsService.updateFile(song.get().getUuid(), file.getInputStream());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private HttpHeaders createFileHeaders(String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", filename));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return headers;
    }

}
