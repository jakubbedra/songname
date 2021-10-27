package com.konfyrm.songname.resource;

import com.konfyrm.songname.dto.CreatePlayerRequest;
import com.konfyrm.songname.dto.GetPlayerResponse;
import com.konfyrm.songname.dto.GetPlayersResponse;
import com.konfyrm.songname.model.Player;
import com.konfyrm.songname.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/players")
@RestController
public class PlayersResource {

    private final PlayersService playersService;

    @Autowired
    public PlayersResource(
            PlayersService playersService
    ) {
        this.playersService = playersService;
    }

    @GetMapping
    public ResponseEntity<GetPlayersResponse> getPlayers() {
        List<Player> players = playersService.getAllPlayers();
        return ResponseEntity.ok(new GetPlayersResponse(players));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<GetPlayerResponse> getPlayer(@PathVariable("uuid") String uuid) {
        Optional<Player> player = playersService.getPlayerByID(UUID.fromString(uuid));
        return player.map(value ->
                ResponseEntity.ok(new GetPlayerResponse(value))
        ).orElseGet(() ->
                ResponseEntity.notFound().build()
        );
    }

    @GetMapping("/game/{uuid}")
    public ResponseEntity<GetPlayersResponse> getPlayers(@PathVariable("uuid") String uuid) {
        List<Player> players = playersService.getPlayersByGameId(UUID.fromString(uuid));
        return ResponseEntity.ok(new GetPlayersResponse(players));
    }

    @PostMapping
    public ResponseEntity<Void> createPlayer(@RequestBody CreatePlayerRequest request, UriComponentsBuilder builder) {
        /*Player player = new Player(
                request.getUuid(), //todo: maybe its better to generate the players (not game's) uuid in here
                request.getName(),
                request.getScore(), //same goes here, just 0 it
                request.getGameUuid()
        );*/
        Player player = new Player(
                request.getName(),
                request.getGameUuid()
        );
        playersService.addPlayer(player);
        return ResponseEntity.created(builder.pathSegment("api", "players", "{uuid}")
                .buildAndExpand(player.getUuid()).toUri()).build();
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("uuid") String uuid) {
        Optional<Player> player = playersService.getPlayerByID(UUID.fromString(uuid));
        if (player.isPresent()) {
            playersService.removePlayerByID(player.get().getUuid());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{uuid}")
    public ResponseEntity<Void> updatePlayer(@PathVariable("uuid") String uuid) {
        Optional<Player> player = playersService.getPlayerByID(UUID.fromString(uuid));
        if (player.isPresent()) {
            Player newPlayer = new Player(
                    player.get().getUuid(),
                    player.get().getName(),
                    player.get().getScore(),
                    player.get().getGameUuid()
            );
            playersService.updatePlayer(newPlayer);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
