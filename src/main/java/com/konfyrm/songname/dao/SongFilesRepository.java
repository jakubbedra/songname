package com.konfyrm.songname.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URL;
import java.util.UUID;

@Repository
public class SongFilesRepository {

    private final String basePath;

    @Autowired
    public SongFilesRepository() {
        URL res = getClass().getClassLoader().getResource("songs/");
        //System.out.println(res);
        this.basePath = res.getPath();
    }

    public synchronized FileInputStream getById(UUID uuid) throws FileNotFoundException {
        File file = new File(basePath + uuid + ".mp3");
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
        File outputFile = new File(basePath + uuid + ".mp3");
        //System.out.println(basePath + uuid + ".mp3");
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(file);
            outputStream.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    public synchronized void deleteById(UUID uuid) {

    }

}
