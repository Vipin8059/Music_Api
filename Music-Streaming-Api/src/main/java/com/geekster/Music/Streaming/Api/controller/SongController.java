package com.geekster.Music.Streaming.Api.controller;

import com.geekster.Music.Streaming.Api.model.Song;
import com.geekster.Music.Streaming.Api.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SongController {
    @Autowired
    SongService songService;

    @GetMapping("songs")
    public List<Song> getAllSongs(){
        return songService.getAllSongs();
    }
}
