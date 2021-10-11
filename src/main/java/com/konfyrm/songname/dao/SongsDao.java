package com.konfyrm.songname.dao;

import com.konfyrm.songname.model.Song;
import com.konfyrm.songname.storage.FakeDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongsDao {

    private FakeDatabase database;

    @Autowired
    public SongsDao(FakeDatabase database) {
        this.database = database;
    }

    public List<Song> getAllSongs(){
        return ;
    }

}
