package com.example.book_borrowing.vo.response;

import com.example.book_borrowing.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 * JI.
 * 表示用戶響應的類，用於返回用戶列表和用戶相關信息
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

  public List<User> userList;

  public String message;

  public Integer userId;

  public String phone;

  public String name;

  public UserResponse(String message) {
    this.message = message;
  }

  public UserResponse(List<User> userList, String message) {
    this.userList = userList;
    this.message = message;
  }

  /**
   * 帶有用戶ID、電話號碼、用戶名和消息的響應構造函數.
   *
   * @param userId 用戶ID
   * @param phone 電話號碼
   * @param name 用戶名
   * @param message 響應消息
   */
  public UserResponse(Integer userId, String phone, String name, String message) {
    this.userId = userId;
    this.phone = phone;
    this.name = name;
    this.message = message;
  }

  public List<User> getUserList() {
    return userList;
  }

  public void setUserList(List<User> userList) {
    this.userList = userList;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
