package com.example.book_borrowing.service.ifs;

import com.example.book_borrowing.entity.User;
import com.example.book_borrowing.vo.response.UserResponse;

/**
 * JI.
 * 提供用戶相關的服務接口。
 */
public interface UserService {

  UserResponse addUser(User user);

  UserResponse isValidUser(String phone, String password);
}
