package com.geekster.Music.Streaming.Api.service;

import com.geekster.Music.Streaming.Api.model.Song;
import com.geekster.Music.Streaming.Api.repository.ISongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {
    @Autowired
    ISongRepo songRepo;

    @Autowired
    PlaylistService playlistService;

    public String addSong(Song song) {
        songRepo.save(song);
        return "song added successfully.";
    }

    public List<Song> getAllSongs() {
       return songRepo.findAll();
    }

    public String deleteSong(Integer songId) {
        Song song = songRepo.findFirstBySongId(songId);
        if(song==null){
            return "Song didn't found";
        }
        songRepo.deleteById(songId);
        return "song deleted successfully";
    }

    public String updateSong(Integer songId,String songName,String songArtist) {
        Song song = songRepo.findFirstBySongId(songId);
        if(song==null){
            return "Song didn't found";
        }
        song.setSongArtist(songArtist);
        song.setSongName(songName);
        songRepo.save(song);
        return "song updated successfully";
    }

    public String addSongToPlaylist(Integer playlistId, List<Integer> songId) {
        List<Song> songList = new ArrayList<Song>();
        for(Integer ele:songId){
            Song song = songRepo.findFirstBySongId(ele);
            if(song==null){
                return "song not found,please enter a valid song";
            }
            else{
                songList.add(song);
            }
        }

        return playlistService.addSongToPlaylist(playlistId,songList);
    }
}
