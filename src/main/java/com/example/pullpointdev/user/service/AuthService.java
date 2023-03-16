package com.example.pullpointdev.user.service;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.user.model.dto.ApproveTokenResp;
import com.example.pullpointdev.user.model.dto.UpdateUserReq;
import com.example.pullpointdev.user.model.dto.UserDTO;

import java.util.List;

public interface AuthService {
    String generateAndSendToken(String phone);

    ApproveTokenResp checkToken(String phone, String token);

    UserDTO updateUser(UpdateUserReq req);

    ApproveTokenResp refresh(String phone);

    String getRandomString(int length);

    boolean checkUsername(String username);

    List<Artist> getArtists(String phone);
}
