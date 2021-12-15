package com.konfyrm.songname.songs.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Repository
public class SongFilesRepository {

    private final String songFilesDirectory;

    @Autowired
    public SongFilesRepository(
            @Value("${songname.uploaded.files.dir}") String uploadedFilesDirectory
    ) {
        URL res = getClass().getClassLoader().getResource("songs/");
        this.songFilesDirectory = uploadedFilesDirectory + "/songs/";
    }

    /**
     * Returns the file of the song with given uuid.
     *
     * @param uuid A unique identifier of the song.
     * @return A FileInputStream containing the song.
     * @throws FileNotFoundException
     */
    public synchronized FileInputStream getById(UUID uuid) throws FileNotFoundException {
        File file = new File(songFilesDirectory + uuid + ".mp3");
        FileInputStream fileInputStream = new FileInputStream(file);
        return fileInputStream;
    }

    /**
     * Saves the given song file in resources folder.
     *
     * @param uuid A unique identifier of the song.
     * @param file Bytes of the file we want to upload.
     * @throws IOException
     */
    public synchronized void save(UUID uuid, byte[] file) throws IOException {
        File outputFile = new File(songFilesDirectory + uuid + ".mp3");
        //System.out.println(basePath + uuid + ".mp3");
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(file);
            outputStream.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Deletes the song file.
     *
     * @param uuid The unique identifier of the song.
     */
    public synchronized void deleteById(UUID uuid) {
        boolean deleted = false;
        File file = new File(songFilesDirectory + uuid + ".mp3");
        file.delete();
    }

}
