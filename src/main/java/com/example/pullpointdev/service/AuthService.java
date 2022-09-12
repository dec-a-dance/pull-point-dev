package com.example.pullpointdev.service;

import com.example.pullpointdev.dto.ApproveTokenResp;
import com.example.pullpointdev.dto.UpdateUserReq;
import com.example.pullpointdev.entity.Artist;
import com.example.pullpointdev.entity.User;
import com.example.pullpointdev.entity.UserAuth;
import com.example.pullpointdev.repository.ArtistRepository;
import com.example.pullpointdev.repository.UserAuthRepository;
import com.example.pullpointdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;
    private final ArtistRepository artistRepository;

    private final JavaMailSender jms;

    public void generateAndSendToken(String phone){
        String newToken = getRandomString(6);
        User user = userRepository.findByPhone(phone);
        if (user == null){
            user = new User();
            user.setPhone(phone);
            user.setIsArtist(false);
            userRepository.save(user);
            UserAuth newAuth = new UserAuth();
            newAuth.setOwner(user);
            newAuth.setUsed(false);
            userAuthRepository.save(newAuth);
        }
        UserAuth auth = userAuthRepository.findByOwner(user);
        auth.setUsed(false);
        auth.setToken(newToken);
        userAuthRepository.save(auth);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(phone);
        msg.setSubject("Код подтверждения");
        String text = String.format("Здравствуйте!\n\n" +
                        "Ваш код: %s\n\n" +
                        "С уважением, команда Pull Point.", newToken);
        msg.setText(text);
        jms.send(msg);
    }

    public ApproveTokenResp checkToken(String phone, String token){
        User user = userRepository.findByPhone(phone);
        UserAuth auth = userAuthRepository.findByOwner(user);
        boolean result = (Objects.equals(token, auth.getToken()))&&!auth.isUsed();
        if (result){
            auth.setUsed(true);
            userAuthRepository.save(auth);
        }
        return new ApproveTokenResp(result, user);
    }

    public User updateUser(UpdateUserReq req){
        User user = userRepository.findByPhone(req.getPhone());
        user.setUsername(req.getUsername());
        user = userRepository.save(user);
        return user;
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


}
