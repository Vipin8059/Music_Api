package com.geekster.Music.Streaming.Api.service;

import com.geekster.Music.Streaming.Api.model.Admin;
import com.geekster.Music.Streaming.Api.model.Song;
import com.geekster.Music.Streaming.Api.model.dto.SignInInput;
import com.geekster.Music.Streaming.Api.model.dto.SignUpOutput;
import com.geekster.Music.Streaming.Api.repository.IAdminRepo;
import com.geekster.Music.Streaming.Api.service.passwordUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {
    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    SongService songService;


    public SignUpOutput signUpAdmin(Admin admin) {
        boolean signUpStatus = false;
        String signUpStatusMessage = null;

        String newEmail = admin.getAdminEmail();
        if(newEmail==null){
            signUpStatusMessage = "Email cannot be empty!!";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(newEmail);
        if(existingAdmin!=null){
            signUpStatusMessage = "Email is already registered!!";
            return  new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        try {
            String encryptPassword = PasswordEncrypter.encryptPassword(admin.getAdminPassword());
            admin.setAdminPassword(encryptPassword);
            adminRepo.save(admin);
            signUpStatus = true;
            signUpStatusMessage = "admin added successfully";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);

        } catch (Exception e) {
            signUpStatusMessage = "Internal server error";
            return new SignUpOutput(false,signUpStatusMessage);
        }

    }

    public String signInAdmin(SignInInput signInInput) {
        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();
        if(signInEmail==null){
            signInStatusMessage = "Email cannot be empty!!!";
            return signInStatusMessage;
        }
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(signInEmail);
        if(existingAdmin==null){
            signInStatusMessage = "Email is not registered!!!";
            return signInStatusMessage;
        }

        try {
            String encryptPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(encryptPassword.equals(existingAdmin.getAdminPassword())){
                return authenticationService.createAdminToken(existingAdmin);
            }
            else{
                signInStatusMessage = "invalid credentials";
                return signInStatusMessage;
            }
        } catch (NoSuchAlgorithmException e) {
            signInStatusMessage = "internal error occurred";
            return signInStatusMessage;
        }

    }

    public String signOutAdmin(String email, String token) {
        String signOutStatusMessage = null;
        if(email==null){
            signOutStatusMessage = "Email cannot be empty.";
            return signOutStatusMessage;
        }
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);
        if(existingAdmin==null){
            signOutStatusMessage = "Not a valid email";
            return signOutStatusMessage;
        }
        return authenticationService.checkAdminToken(token);

    }

    public String addSong(Song song, String email) {
        if(email==null){
            return "Email cannot be empty!!";
        }
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);
        if(existingAdmin==null){
            return "Not a valid email to add song";
        }
        song.setAdmin(existingAdmin);
        return songService.addSong(song);
    }

    public String deleteSong(Integer songId, String email, String token) {
        if(email==null){
            return "Email cannot be empty!!";
        }
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);
        if(existingAdmin==null){
            return "Not a valid email to delete song";
        }
        return authenticationService.checkAdminToken(songId,token);
    }

    public String updateSong(Integer songId,String songName, String songArtist, String email, String token) {
        if(email==null){
            return "Email cannot be empty!!";
        }
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(email);
        if(existingAdmin==null){
            return "Not a valid email to delete song";
        }
        return authenticationService.checkAdminTokenForUpdate(songId,songName,songArtist,token);
    }
}
