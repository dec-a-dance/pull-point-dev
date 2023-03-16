package com.example.pullpointdev.user.service;

import com.example.pullpointdev.artist.model.Artist;
import com.example.pullpointdev.security.JwtUtil;
import com.example.pullpointdev.security.Role;
import com.example.pullpointdev.user.model.dto.ApproveTokenResp;
import com.example.pullpointdev.user.model.dto.UpdateUserReq;
import com.example.pullpointdev.user.model.User;
import com.example.pullpointdev.artist.repository.ArtistRepository;
import com.example.pullpointdev.user.model.dto.UserDTO;
import com.example.pullpointdev.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final JwtUtil jwtUtil;
    private final JavaMailSender jms;

    public String generateAndSendToken(String phone){
        String newToken = getRandomString(6);
        User user = userRepository.findByPhone(phone).orElse(null);
        if (user == null){
            user = new User();
            user.setPhone(phone);
            user.setRole(Role.ROLE_USER);
            user.setIsArtist(false);
            userRepository.save(user);
        }
        user.setToken(newToken);
        /*SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(phone);
        msg.setSubject("Код подтверждения");
        String text = String.format("Здравствуйте!\n\n" +
                        "Ваш код: %s\n\n" +
                        "С уважением, команда Pull Point.", newToken);
        msg.setText(text);
        jms.send(msg);
        */
        return newToken;
    }

    public ApproveTokenResp checkToken(String phone, String token){
        User user = userRepository.findByPhone(phone).orElseThrow(NullPointerException::new);
        boolean result = (Objects.equals(token, user.getToken()));
        if (result){
            user.setToken("not a token");
            userRepository.save(user);
        }
        String jwt = jwtUtil.generateToken(user.getPhone());
        return new ApproveTokenResp(result, jwt, user);
    }

    public UserDTO updateUser(UpdateUserReq req){
        User user = userRepository.findByPhone(req.getPhone()).orElseThrow(NullPointerException::new);
        user.setUsername(req.getUsername());
        user.setNotificationsToken(req.getNotificationsToken());
        user = userRepository.save(user);
        return user.convertToDto();
    }

    public ApproveTokenResp refresh(String phone){
        return new ApproveTokenResp(true, jwtUtil.generateToken(phone), userRepository.findByPhone(phone).orElseThrow(() -> new NullPointerException("no such user")));
    }


    public String getRandomString(int length){
        String str="0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public boolean checkUsername(String username){
        if (userRepository.findByUsername(username).orElse(null) == null){
            return true;
        }
        else return false;
    }

    public List<Artist> getArtists(String phone){
        return userRepository.findByPhone(phone).orElseThrow(() -> new NullPointerException("no such user")).getArtists();
    }

}
