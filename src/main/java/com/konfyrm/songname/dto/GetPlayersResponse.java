package com.konfyrm.songname.dto;

import com.konfyrm.songname.model.Player;

import java.util.List;
import java.util.UUID;

public class GetPlayersResponse {

    public class PlayerDto {
        private UUID uuid;
        private String name;
        private int score;

        public PlayerDto(UUID uuid, String name, int score) {
            this.uuid = uuid;
            this.name = name;
            this.score = score;
        }

        public UUID getUuid() {
            return uuid;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }

    private List<PlayerDto> players;

    public GetPlayersResponse(List<Player> players) {
        players.forEach(p ->
                this.players.add(new PlayerDto(p.getUuid(), p.getName(), p.getScore()))
        );
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

}
