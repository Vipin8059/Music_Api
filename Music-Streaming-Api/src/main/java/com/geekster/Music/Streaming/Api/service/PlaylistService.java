package com.geekster.Music.Streaming.Api.service;

import com.geekster.Music.Streaming.Api.model.Playlist;
import com.geekster.Music.Streaming.Api.model.Song;
import com.geekster.Music.Streaming.Api.model.User;
import com.geekster.Music.Streaming.Api.repository.IPlaylistRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    IPlaylistRepo playlistRepo;



    public String createPlaylist(Playlist playlist) {
        playlistRepo.save(playlist);
        return "playlist added successfully";
    }

    public List<Playlist> getPlaylist(User user) {
        Integer userId = user.getUserId();
        List<Playlist> playlists = playlistRepo.findFirstByUser(user);
        return playlists;
    }

    public String deletePlaylist(User user) {
        List<Playlist> playlists = playlistRepo.findFirstByUser(user);
        playlistRepo.deleteAll(playlists);
        return "playlist removed successfully";
    }

    public String addSongToPlaylist(Integer playlistId, List<Song> songList) {
        Playlist playlist = playlistRepo.findFirstByPlaylistId(playlistId);
        playlist.setSongList(songList);
        playlistRepo.save(playlist);
        return "song added successfully to playlist.";
    }
}
