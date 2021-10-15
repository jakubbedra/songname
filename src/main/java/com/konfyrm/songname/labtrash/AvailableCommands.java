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

    public final String label;

    private AvailableCommands(String label) {
        this.label = label;
    }

    public static AvailableCommands valueOfLabel(String label) {
        for (AvailableCommands cmd : values()) {
            if (cmd.label.equals(label)) {
                return cmd;
            }
        }
        return null;
    }

}
