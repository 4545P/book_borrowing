package com.example.book_borrowing.vo.request;

import java.time.LocalDateTime;

/**
 * JI.
 * 表示用戶請求的類，用於接收用戶相關的請求數據
 */
public class UsersRequest {

  public Integer userId;

  public String phone;

  public String name;

  public String password;

  public LocalDateTime registration;

  public LocalDateTime lastLogin;

  public UsersRequest() {

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDateTime getRegistration() {
    return registration;
  }

  public void setRegistration(LocalDateTime registration) {
    this.registration = registration;
  }

  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }
}
