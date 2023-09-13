package com.example.book_borrowing.service.ifs;

import com.example.book_borrowing.entity.User;
import com.example.book_borrowing.vo.response.UserResponse;

public interface UserService {

    public UserResponse addUser(User user);

    public UserResponse isValidUser(String phone, String password);
}
