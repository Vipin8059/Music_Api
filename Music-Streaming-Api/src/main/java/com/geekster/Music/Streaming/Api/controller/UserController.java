package com.geekster.Music.Streaming.Api.controller;

import com.geekster.Music.Streaming.Api.model.Admin;
import com.geekster.Music.Streaming.Api.model.Playlist;
import com.geekster.Music.Streaming.Api.model.User;
import com.geekster.Music.Streaming.Api.model.dto.SignInInput;
import com.geekster.Music.Streaming.Api.model.dto.SignUpOutput;
import com.geekster.Music.Streaming.Api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("signUp/user")
    public SignUpOutput signUpUser(@RequestBody @Valid User user){
        return userService.signUpUser(user);
    }

    @PostMapping("signIn/user")
    public String signInUser(@RequestBody @Valid SignInInput signInInput){
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("signOut/user")
    public String signOutAdmin(@RequestParam String email, @RequestParam String token){
        return userService.signOutUser(email,token);
    }

    @PostMapping("playlist/create")
    public String addPlaylist(@RequestBody @Valid Playlist playlist,@RequestParam String email, @RequestParam String token){
        return userService.createPlaylist(playlist,email,token);
    }

    @GetMapping("playlists")
    public List<Playlist> getPlaylists(@RequestParam String email){
        return userService.getPlaylists(email);
    }

    @DeleteMapping("playlist/delete")
    public String deletePlaylist(@RequestParam String email, @RequestParam String token){
        return userService.deletePlaylist(email,token);
    }

    @PutMapping("update/playlist")
    public String addSongToPlaylist(@RequestParam Integer playlistId,@RequestParam List<Integer> songId,@RequestParam String  email,@RequestParam String token ){
        return userService.addSongToPlaylist(playlistId,songId,email,token);
    }
}
