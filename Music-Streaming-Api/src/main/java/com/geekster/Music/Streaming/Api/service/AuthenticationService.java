package com.geekster.Music.Streaming.Api.service;

import com.geekster.Music.Streaming.Api.model.Admin;
import com.geekster.Music.Streaming.Api.model.AuthenticationToken;
import com.geekster.Music.Streaming.Api.model.Playlist;
import com.geekster.Music.Streaming.Api.model.User;
import com.geekster.Music.Streaming.Api.repository.IAuthenticationRepo;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authenticationRepo;

    @Autowired
    SongService songService;

    @Autowired
    PlaylistService playlistService;

    public String createAdminToken(Admin existingAdmin) {
        AuthenticationToken authToken = new AuthenticationToken(existingAdmin);
        authenticationRepo.save(authToken);
        return "Token generated successfully.";
    }
    public String createUserToken(User existingUser) {
        AuthenticationToken authToken = new AuthenticationToken(existingUser);
        authenticationRepo.save(authToken);
        return "Token generated successfully.";
    }

    public String checkAdminToken(String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
       AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        authenticationRepo.delete(existingToken);
        tokenMessage = "Admin signOut successfully.";
        return tokenMessage;
    }
    public String checkAdminToken(Integer songId,String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        return songService.deleteSong(songId);
    }


    public String checkUserToken(String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        authenticationRepo.delete(existingToken);
        tokenMessage = "User signOut successfully.";
        return tokenMessage;
    }

    public String checkAdminTokenForUpdate(Integer songId,String songName, String songArtist,String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        return songService.updateSong(songId,songName,songArtist);
    }

    public String checkUser(Playlist playlist, String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        return playlistService.createPlaylist(playlist);
    }

    public String deletePlaylist(User existingUser, String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        return playlistService.deletePlaylist(existingUser);
    }

    public String addSongToPlayList(Integer playlistId, List<Integer> songId, String token) {
        String tokenMessage = null;
        if(token ==null){
            tokenMessage = "token cannot be null";
            return  tokenMessage;
        }
        AuthenticationToken existingToken =  authenticationRepo.findFirstByTokenValue(token);
        if(existingToken==null){
            tokenMessage = "invalid Token";
            return tokenMessage;
        }
        return songService.addSongToPlaylist(playlistId,songId);
    }
}
