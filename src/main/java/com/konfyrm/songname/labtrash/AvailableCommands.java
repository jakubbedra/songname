package com.konfyrm.songname.labtrash;

public enum AvailableCommands {

    HELP("help"),
    SHUTDOWN("shutdown"),
    AUTHORS_ADD("authors -add"),
    AUTHORS_ALL("authors -all"),
    AUTHORS_FIND("authors -find"),
    AUTHORS_REMOVE("authors -remove"),
    SONGS_ADD("songs -add"),
    SONGS_ALL("songs -all"),
    SONGS_FIND("songs -find"),
    SONGS_REMOVE("songs -remove");

    private final String label;

    AvailableCommands(String label) {
        this.label = label;
    }
}
