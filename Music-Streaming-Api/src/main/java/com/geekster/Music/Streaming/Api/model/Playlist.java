package com.geekster.Music.Streaming.Api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistId;

    @NotBlank
    private String playlistName;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "song_playlist_table",joinColumns = @JoinColumn(name = "fk_playList_id"),inverseJoinColumns = @JoinColumn(name = "fk_song_id"))
    private List<Song> songList;
}
