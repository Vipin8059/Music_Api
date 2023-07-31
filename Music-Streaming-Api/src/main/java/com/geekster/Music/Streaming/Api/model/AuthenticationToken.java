package com.geekster.Music.Streaming.Api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;

    private String tokenValue;

    private LocalDateTime tokeCreationDate;

    @OneToOne
    @JoinColumn(name = "fk_admin_id")
    private Admin admin;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    public AuthenticationToken(Admin admin){
        this.admin = admin;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokeCreationDate = LocalDateTime.now();
    }

    public AuthenticationToken(User user){
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokeCreationDate = LocalDateTime.now();
    }

}
