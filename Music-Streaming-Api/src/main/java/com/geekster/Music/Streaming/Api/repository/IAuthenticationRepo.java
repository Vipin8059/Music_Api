package com.geekster.Music.Streaming.Api.repository;

import com.geekster.Music.Streaming.Api.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByTokenValue(String token);
}
