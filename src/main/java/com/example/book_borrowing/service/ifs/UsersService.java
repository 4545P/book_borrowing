package com.example.book_borrowing.service.ifs;

import com.example.book_borrowing.entity.Users;
import com.example.book_borrowing.vo.response.UsersResponse;

/**
 * JI.
 * 提供用戶相關的服務接口。
 */
public interface UsersService {

  UsersResponse addUser(Users user);

  UsersResponse isValidUser(String phone, String password);
}
