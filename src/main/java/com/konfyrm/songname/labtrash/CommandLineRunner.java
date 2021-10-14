package com.konfyrm.songname.labtrash;

import com.konfyrm.songname.SongnameApplication;
import com.konfyrm.songname.service.AuthorsService;
import com.konfyrm.songname.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineRunner {

    private final SongsService songsService;
    private final AuthorsService authorsService;

    @Autowired
    public CommandLineRunner(
            SongsService songsService,
            AuthorsService authorsService
    ) {
        this.songsService = songsService;
        this.authorsService = authorsService;
    }

    @PostConstruct
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("authors -all")) {

            } else if (input.equals("authors -add")) {

            } else if (input.equals("authors -remove")) {

            } else if (input.equals("songs -all")) {

            } else if (input.equals("songs -add")) {
                addSong(scanner);
            } else if (input.equals("songs -remove")) {
                removeSong(scanner);
            } else if (input.equals("commands")) {
                listCommands();
            } else if (input.equals("shutdown")) {
                shutdown();
            }
        }
    }

    private void addSong(Scanner scanner) {

    }

    private void removeSong(Scanner scanner) {
        UUID uuid = UUID.fromString(scanner.nextLine());
        songsService.removeSongById(uuid);
    }

    private void listCommands() {
        System.out.println("authors -all \n>>lists all authors");
        System.out.println("authors -add \n[name]\n>>adds an author");
        System.out.println("authors -remove \n[uuid]\n>>removes an author");
        System.out.println("songs -all \n>>lists all songs");
        System.out.println("songs -add \n[name]\n[author]\n>>adds a song");
        System.out.println("songs -remove \n[uuid]\n>>removes a song");
    }

    private void shutdown() {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SongnameApplication.class)
                .web(WebApplicationType.NONE).run();
        SpringApplication.exit(ctx, () -> 0);
    }

}
