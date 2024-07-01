package com.example.book_borrowing.entity;



import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * JI.
 * 用戶信息
 */
@Component
@Entity
@Table(name = "User")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "phone_number")
  private String phone;

  @Column(name = "user_name")
  private String name;

  @Column(name = "password")
  private String password;

  @Column(name = "registration")
  private LocalDateTime registration;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  public User() {

  }

  /**
   * 帶參構造函數.
   *
   * @param userId 用戶ID
   * @param phone 電話號碼
   * @param name 用戶名
   * @param password 密碼
   * @param registration 註冊時間
   * @param lastLogin 最後登錄時間
   */
  public User(Integer userId, String phone, String name, String password, LocalDateTime registration, LocalDateTime lastLogin) {
    this.userId = userId;
    this.phone = phone;
    this.name = name;
    this.password = password;
    this.registration = registration;
    this.lastLogin = lastLogin;
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
