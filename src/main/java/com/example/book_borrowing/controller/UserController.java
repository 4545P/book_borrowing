package com.example.book_borrowing.controller;

import com.example.book_borrowing.entity.User;
import com.example.book_borrowing.service.ifs.UserService;
import com.example.book_borrowing.vo.request.UserRequest;
import com.example.book_borrowing.vo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/add")
    public UserResponse addUser(@RequestBody User user) {
        return  userService.addUser(user);
    }

    @PostMapping("/api/user/isValid")
    public UserResponse isValidUser(@RequestBody UserRequest userRequest) {
        return  userService.isValidUser(userRequest.getPhone(), userRequest.getPassword());
    }
}
