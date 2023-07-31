package com.geekster.Music.Streaming.Api.controller;

import com.geekster.Music.Streaming.Api.model.Admin;
import com.geekster.Music.Streaming.Api.model.Song;
import com.geekster.Music.Streaming.Api.model.dto.SignInInput;
import com.geekster.Music.Streaming.Api.model.dto.SignUpOutput;
import com.geekster.Music.Streaming.Api.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("signUp/admin")
    public SignUpOutput signUpAdmin(@RequestBody @Valid Admin admin){
        return adminService.signUpAdmin(admin);
    }

    @PostMapping("signIn/admin")
    public String signInAdmin(@RequestBody @Valid SignInInput signInInput){
        return adminService.signInAdmin(signInInput);
    }

    @DeleteMapping("signOut/admin")
    public String signOutAdmin(@RequestParam String email, @RequestParam String token){
        return adminService.signOutAdmin(email,token);
    }

    @PostMapping("song/add")
    public String addSong(@RequestBody @Valid Song song,@RequestParam String adminEmail){
        return adminService.addSong(song,adminEmail);
    }

    @DeleteMapping("song/delete")
    public String deleteSong(@RequestParam Integer songId, @RequestParam String email,@RequestParam String token){
        return adminService.deleteSong(songId,email,token);
    }

    @PutMapping("song/update")
    public String updateSong(@RequestParam Integer songId,@RequestParam String songName,@RequestParam String songArtist,@RequestParam String email, @RequestParam String token){
        return adminService.updateSong(songId,songName,songArtist,email,token);
    }
}
