package com.konfyrm.songname.players.resource;

import com.konfyrm.songname.players.dto.CreatePlayerRequest;
import com.konfyrm.songname.players.dto.GetPlayerResponse;
import com.konfyrm.songname.players.dto.GetPlayersResponse;
import com.konfyrm.songname.players.dto.UpdatePlayerScore;
import com.konfyrm.songname.players.model.Player;
import com.konfyrm.songname.players.service.PlayersService;
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

    @PostMapping
    public ResponseEntity<Void> createPlayer(@RequestBody CreatePlayerRequest request, UriComponentsBuilder builder) {
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
    public ResponseEntity<Void> updatePlayer(@PathVariable("uuid") String uuid,
                                             @RequestBody UpdatePlayerScore request) {
        Optional<Player> player = playersService.getPlayerByID(UUID.fromString(uuid));
        if (player.isPresent()) {
            Player newPlayer = new Player(
                    player.get().getUuid(),
                    player.get().getName(),
                    player.get().getScore() + request.getPoints(),
                    player.get().getGameUuid()
            );
            playersService.updatePlayer(newPlayer);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
