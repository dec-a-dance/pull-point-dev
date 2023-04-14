package com.example.pullpointdev.moderation.service;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.artist.model.ArtistVerificationStatus;
import com.example.pullpointdev.artist.repository.ArtistRepository;
import com.example.pullpointdev.moderation.exception.ModerationAuthException;
import com.example.pullpointdev.moderation.model.req.AuthModeratorReq;
import com.example.pullpointdev.security.JwtUtil;
import com.example.pullpointdev.security.Role;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.model.dto.ApproveTokenResp;
import com.example.pullpointdev.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModerationService {
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;


    public User registerModerator(AuthModeratorReq req) {
        User user = userRepository.findByPhone(req.getUsername()).orElse(null);
        if (user == null){
            user = new User();
            user.setPhone(req.getUsername());
            user.setRole(Role.ROLE_ADMIN);
            userRepository.save(user);
            user.setToken(encoder.encode(req.getPassword()));
            return user;
        }
        else{
            throw new ModerationAuthException("such user already exists");
        }
    }

    public ApproveTokenResp loginModerator(AuthModeratorReq req){
        User user = userRepository.findByPhone(req.getUsername()).orElseThrow();
        if (encoder.matches(req.getPassword(), user.getPassword())){
            String jwt = jwtUtil.generateToken(user.getPhone());
            return new ApproveTokenResp(true, jwt, user);
        }
        else{
            throw new ModerationAuthException("wrong password");
        }
    }

    @SneakyThrows
    public List<Artist> getArtistsByStatus(String status){
        return artistRepository.findAllByVerification(ArtistVerificationStatus.valueOf(status.toUpperCase()));
    }

    public Artist verifyArtist(Long id){
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NullPointerException("no such artist"));
        artist.setVerification(ArtistVerificationStatus.VERIFIED);
        return artistRepository.save(artist);
    }

    public Artist declineArtist(Long id){
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NullPointerException("no such artist"));
        artist.setVerification(ArtistVerificationStatus.DECLINED);
        return artistRepository.save(artist);
    }

}
