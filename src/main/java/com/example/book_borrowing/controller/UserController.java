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


/**
 * JI.
 * 控制器類，負責處理與用戶相關的API請求
 */
@CrossOrigin
@RestController
public class UserController {

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  /**
   * 添加新用戶.
   *
   * @param user 包含用戶信息的請求對象
   * @return 返回包含用戶添加結果的響應對象
   */
  @PostMapping("/api/user/add")
  public UserResponse addUser(@RequestBody User user) {
    return  userService.addUser(user);
  }

  /**
   * 驗證用戶有效性.
   *
   * @param userRequest 包含用戶電話號碼和密碼的請求對象
   * @return 返回包含用戶驗證結果的響應對象
   */
  @PostMapping("/api/user/isValid")
  public UserResponse isValidUser(@RequestBody UserRequest userRequest) {
    return  userService.isValidUser(userRequest.getPhone(), userRequest.getPassword());
  }
}
