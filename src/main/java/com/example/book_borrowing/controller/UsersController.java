package com.example.book_borrowing.controller;

import com.example.book_borrowing.entity.Users;
import com.example.book_borrowing.service.ifs.UsersService;
import com.example.book_borrowing.vo.request.UsersRequest;
import com.example.book_borrowing.vo.response.UsersResponse;
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
public class UsersController {

  private UsersService userService;

  @Autowired
  public void setUserService(UsersService userService) {
    this.userService = userService;
  }

  /**
   * 添加新用戶.
   *
   * @param user 包含用戶信息的請求對象
   * @return 返回包含用戶添加結果的響應對象
   */
  @PostMapping("/api/user/add")
  public UsersResponse addUser(@RequestBody Users user) {
    return  userService.addUser(user);
  }

  /**
   * 驗證用戶有效性.
   *
   * @param userRequest 包含用戶電話號碼和密碼的請求對象
   * @return 返回包含用戶驗證結果的響應對象
   */
  @PostMapping("/api/user/isValid")
  public UsersResponse isValidUser(@RequestBody UsersRequest userRequest) {
    return  userService.isValidUser(userRequest.getName(), userRequest.getPassword());
  }
}
