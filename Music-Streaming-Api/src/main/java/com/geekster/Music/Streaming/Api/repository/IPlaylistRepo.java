package com.geekster.Music.Streaming.Api.repository;

import com.geekster.Music.Streaming.Api.model.Playlist;
import com.geekster.Music.Streaming.Api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlaylistRepo extends JpaRepository<Playlist,Integer> {


    List<Playlist> findFirstByUser(User user);

    Playlist findFirstByPlaylistId(Integer playlistId);
}
