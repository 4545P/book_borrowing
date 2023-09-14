package com.example.book_borrowing.service.impl;

import com.example.book_borrowing.entity.User;
import com.example.book_borrowing.repository.UserDao;
import com.example.book_borrowing.service.ifs.UserService;
import com.example.book_borrowing.vo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collections;

@EnableScheduling
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserResponse addUser(User user) {

        if(user.getPhone().isBlank() || user.getPassword().isBlank() || user.getName().isBlank()) {
            return new UserResponse(Collections.singletonList(user), "註冊失敗");
        } else {
            LocalDateTime registerTime = LocalDateTime.now();
            user.setRegistration(registerTime);
            String hashedPassword = hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
        }
        userDao.save(user);
        return new UserResponse(Collections.singletonList(user), "註冊成功");
    }

    private  String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());

            String hashedPassword = Base64.getEncoder().encodeToString(hashBytes);
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserResponse isValidUser(String phone, String password) {

        User user = userDao.findByPhone(phone);

        if (user != null) {
            String storedHashedPassword = user.getPassword();
            if (verifyPassword(password, storedHashedPassword)) {
                LocalDateTime lastLoginTime = LocalDateTime.now();
                user.setLastLogin(lastLoginTime);
                userDao.save(user);
                return new UserResponse(user.getUserId(), user.getPhone(), user.getName(), "登入成功");
            }
        }
        return new UserResponse("登入失敗");
    }

    public boolean verifyPassword(String inputPassword, String storedHashedPassword) {
        String hashedInputPassword = hashPassword(inputPassword);
        return hashedInputPassword.equals(storedHashedPassword);
    }


}
