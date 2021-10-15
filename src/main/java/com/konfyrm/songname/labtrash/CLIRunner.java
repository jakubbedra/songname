package com.konfyrm.songname.labtrash;

import com.konfyrm.songname.model.Author;
import com.konfyrm.songname.model.Song;
import com.konfyrm.songname.service.AuthorsService;
import com.konfyrm.songname.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CLIRunner {

    private final SongsService songsService;
    private final AuthorsService authorsService;

    @Autowired
    public CLIRunner(
            SongsService songsService,
            AuthorsService authorsService
    ) {
        this.songsService = songsService;
        this.authorsService = authorsService;
    }

    @PostConstruct
    private void runCli() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            AvailableCommands cmd = AvailableCommands.valueOf(scanner.nextLine());
            switch (cmd) {
                case HELP:
                    help();
                    break;
                case SHUTDOWN:
                    shutdown();
                    break;
                case AUTHORS_ADD:
                    addAuthor(scanner);
                    break;
                case AUTHORS_ALL:
                    allAuthors();
                    break;
                case AUTHORS_FIND:
                    findAuthor(scanner);
                    break;
                case AUTHORS_REMOVE:
                    removeAuthor(scanner);
                    break;
                case SONGS_ADD:
                    addSong(scanner);
                    break;
                case SONGS_ALL:
                    allSongs();
                    break;
                case SONGS_FIND:
                    findSong(scanner);
                    break;
                case SONGS_REMOVE:
                    removeSong(scanner);
                    break;
                default:
                    System.out.println("unknown command, type \"help\" for the list of available commands");
                    break;
            }
        }
    }

    private void removeSong(Scanner scanner) {
        UUID uuid = UUID.fromString(scanner.nextLine());
        songsService.removeSongById(uuid);
    }

    private void findSong(Scanner scanner) {
        UUID uuid = UUID.fromString(scanner.nextLine());
        Song song = songsService.getSongById(uuid);
        System.out.println(song);
    }

    private void allSongs() {
        List<Song> songs = songsService.getAllSongs();
        songs.stream().forEach(System.out::println);
    }

    private void addSong(Scanner scanner) {
        String title = scanner.nextLine();
        UUID authorUuid = UUID.fromString(scanner.nextLine());
        Author author = authorsService.getAuthorById(authorUuid);
        songsService.addNewSong(new Song(title, author));
    }

    private void removeAuthor(Scanner scanner) {
        UUID uuid = UUID.fromString(scanner.nextLine());
        authorsService.removeAuthorById(uuid);
    }

    private void findAuthor(Scanner scanner) {
        UUID uuid = UUID.fromString(scanner.nextLine());
        Author author = authorsService.getAuthorById(uuid);
        System.out.println(author);
    }

    private void allAuthors() {
        List<Author> authors = authorsService.getAllAuthors();
        authors.stream().forEach(System.out::println);
    }

    /**
     * Adds a new author to the storage
     *
     * @param scanner A {@code Scanner} for scanning parameters
     */
    private void addAuthor(Scanner scanner) {
        String name = scanner.nextLine();
        authorsService.addNewAuthor(new Author(name));
    }

    /**
     * Shuts down the app
     */
    private void shutdown() {

    }

    /**
     * Prints info about available commands
     */
    private void help() {
        System.out.println(AvailableCommands.HELP + "\n-> prints the info about available commands");
        System.out.println(AvailableCommands.SHUTDOWN + "\n-> closes the application");
        System.out.println(AvailableCommands.AUTHORS_ADD + "\n[name]\n-> adds a new author to the storage");
        System.out.println(AvailableCommands.AUTHORS_ALL + "\n-> prints all authors currently in storage");
        System.out.println(AvailableCommands.AUTHORS_FIND + "\n[uuid]\n-> finds the author with given uuid");
        System.out.println(AvailableCommands.AUTHORS_REMOVE + "\n[uuid]\n-> removes the author with given uuid");
        System.out.println(AvailableCommands.SONGS_ADD + "\n[name]\n[author uuid]\n-> adds a new song to the storage");
        System.out.println(AvailableCommands.SONGS_ALL + "\n-> prints all songs currently in storage");
        System.out.println(AvailableCommands.SONGS_FIND + "\n[uuid]\n-> finds the song with given uuid");
        System.out.println(AvailableCommands.SONGS_REMOVE + "\n[uuid]\n-> removes the song with given uuid");
    }

}