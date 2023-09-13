package com.example.book_borrowing.service.impl;

import com.example.book_borrowing.entity.User;
import com.example.book_borrowing.repository.UserDao;
import com.example.book_borrowing.service.ifs.UserService;
import com.example.book_borrowing.vo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        }
        userDao.save(user);
        return new UserResponse(Collections.singletonList(user), "註冊成功");
    }

    @Override
    public UserResponse isValidUser(String phone, String password) {

        User user = userDao.findByPhone(phone);

        if(user != null) {
            if(password.equals(user.getPassword())) {
                return new UserResponse(user.getUserId(), user.getPhone(), user.getName());
            }
        }

        return new UserResponse("登入失敗");
    }
}
