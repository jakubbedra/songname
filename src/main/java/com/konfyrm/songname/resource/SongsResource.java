package com.konfyrm.songname.resource;

import com.konfyrm.songname.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/songs")
public class SongsResource {

    private final SongsService songsService;

    @Autowired
    public SongsResource(
            SongsService songsService
    ) {
        this.songsService = songsService;
    }



}
