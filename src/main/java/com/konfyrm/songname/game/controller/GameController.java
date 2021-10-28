package com.konfyrm.songname.game.controller;

import com.konfyrm.songname.authors.model.Author;
import com.konfyrm.songname.authors.service.AuthorsService;
import com.konfyrm.songname.game.dto.GameTurnDTO;
import com.konfyrm.songname.game.model.Game;
import com.konfyrm.songname.game.service.GameService;
import com.konfyrm.songname.players.dto.GetPlayersResponse;
import com.konfyrm.songname.players.model.Player;
import com.konfyrm.songname.players.service.PlayersService;
import com.konfyrm.songname.songs.dto.CheckSongRequest;
import com.konfyrm.songname.songs.model.Song;
import com.konfyrm.songname.songs.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/api/game")
@RestController
public class GameController {

    private static final int SONGS_PER_PLAYER = 10; //todo: make it configurable in front-end

    private final GameService gameService;
    private final PlayersService playersService;
    private final SongsService songsService;
    private final AuthorsService authorsService;

    @Autowired
    public GameController(
            GameService gameService,
            PlayersService playersService,
            SongsService songsService,
            AuthorsService authorsService
    ) {
        this.gameService = gameService;
        this.playersService = playersService;
        this.songsService = songsService;
        this.authorsService = authorsService;
    }

    @GetMapping("/uuid")
    public ResponseEntity<UUID> requestUuid() {
        return ResponseEntity.ok(UUID.randomUUID());
    }

    @GetMapping("/{uuid}/turn")
    public ResponseEntity<GameTurnDTO> getCurrentTurn(@PathVariable("uuid") String uuid) {
        Optional<Game> game = gameService.getGame(UUID.fromString(uuid));
        if (game.isPresent()) {
            Player player = playersService.getPlayerByID(game.get().getCurrentPlayer()).get();
            return ResponseEntity.ok(
                    new GameTurnDTO(player, game.get().getCurrentSong(), game.get().getCurrentTurn())
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{uuid}/players")
    public ResponseEntity<GetPlayersResponse> getPlayers(@PathVariable("uuid") String uuid) {
        List<Player> players = playersService.getPlayersByGameId(UUID.fromString(uuid));
        return ResponseEntity.ok(
                new GetPlayersResponse(players)
        );
    }

    @GetMapping("/{uuid}/results")
    public ResponseEntity<GetPlayersResponse> getResults(@PathVariable("uuid") String uuid) {
        List<Player> players = playersService.getPlayersByGameId(UUID.fromString(uuid)).stream()
                .sorted(Comparator.comparingInt(Player::getScore))
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new GetPlayersResponse(players)
        );
    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkSong(@RequestBody CheckSongRequest request) {
        Optional<Song> song = songsService.getSongById(request.getUuid());
        if (song.isPresent()) {
            Author author = song.get().getAuthor();
            return ResponseEntity.ok(
                    gameService.compareAnswer(request.getTitle(), song.get().getTitle()) &&
                            gameService.compareAnswer(request.getAuthorName(), author.getName())
            );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{uuid}/create")
    public ResponseEntity<Void> createGame(@PathVariable("uuid") String uuid) {
        List<Player> players = playersService.getPlayersByGameId(UUID.fromString(uuid));
        if (players.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Song> songs = songsService.getRandomSongs(SONGS_PER_PLAYER * players.size());
        gameService.createGame(UUID.fromString(uuid), players, songs, SONGS_PER_PLAYER);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{uuid}/finish")
    public ResponseEntity<Void> finishGame(@PathVariable("uuid") String uuid) {
        Optional<Game> game = gameService.getGame(UUID.fromString(uuid));
        if (game.isPresent()) {
            List<Player> players = playersService.getPlayersByGameId(UUID.fromString(uuid));
            players.forEach(p -> playersService.removePlayerByID(p.getUuid()));
            gameService.removeGame(UUID.fromString(uuid));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{uuid}/turn/next")
    public ResponseEntity<Boolean> nextTurn(@PathVariable("uuid") String uuid) {
        Optional<Game> game = gameService.getGame(UUID.fromString(uuid));
        if (game.isPresent()) {
            if (!game.get().nextTurn()) {
                return ResponseEntity.ok(false);
            }
            gameService.updateGame(UUID.fromString(uuid), game.get());
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
