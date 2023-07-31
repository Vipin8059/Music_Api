package com.geekster.Music.Streaming.Api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;

    @NotBlank
    private String songName;

    @NotBlank
    private String songArtist;

    @NotNull
    private LocalDate songReleaseDate;

    @ManyToOne
    @JoinColumn(name = "fk_admin_id")
    private Admin admin;


}
