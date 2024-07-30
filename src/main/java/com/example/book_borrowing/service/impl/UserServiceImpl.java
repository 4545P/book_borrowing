package com.example.book_borrowing.service.impl;

import com.example.book_borrowing.entity.User;
import com.example.book_borrowing.repository.UserDao;
import com.example.book_borrowing.service.ifs.UserService;
import com.example.book_borrowing.vo.response.UserResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

/**
 * JI.
 * 用戶服務的實現類，實現了 UserService 接口，並且使用了定時任務功能
 */
@EnableScheduling
@Service
public class UserServiceImpl implements UserService {

  UserDao userDao;

  @Autowired
  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  /**
   * 將使用者註冊到系統中，如果必要的用戶資訊不完整，返回註冊失敗的回應.
   *
   * @param user 要註冊的使用者物件
   * @return 表示註冊結果的 UserResponse 物件
   */
  @Override
  public UserResponse addUser(User user) {
    if (user.getPhone().isBlank() || user.getPassword().isBlank() || user.getName().isBlank()) {
      return new UserResponse(Collections.singletonList(user), "註冊失敗");
    } else {
      LocalDateTime registerTime = LocalDateTime.now();
      user.setRegistration(registerTime);
      String hashedPassword = hashPassword(user.getPassword());
      user.setPassword(hashedPassword);
    }
    userDao.save(user);
    return new UserResponse(Collections.singletonList(user), "註冊成功");
  }

  /**
   * 驗證用戶登錄信息是否有效，如果用戶存在並且密碼正確，更新最後登錄時間並返回登錄成功的回應.
   *
   * @param phone    用戶電話號碼
   * @param password 用戶密碼
   * @return 表示登錄驗證結果的 UserResponse 物件
   */
  @Override
  public UserResponse isValidUser(String phone, String password) {
    User user = userDao.findByPhone(phone);
    if (user != null) {
      String storedHashedPassword = user.getPassword();
      if (verifyPassword(password, storedHashedPassword)) {
        LocalDateTime lastLoginTime = LocalDateTime.now();
        user.setLastLogin(lastLoginTime);
        userDao.save(user);
        return new UserResponse(user.getUserId(), user.getPhone(), user.getName(), "登入成功");
      }
    }
    return new UserResponse("登入失敗");
  }

  /**
   * 驗證輸入密碼與存儲的雜湊密碼是否匹配.
   *
   * @param inputPassword        輸入的密碼
   * @param storedHashedPassword 存儲的雜湊密碼
   * @return 如果輸入密碼與存儲的雜湊密碼匹配，返回 true；否則返回 false
   */
  public boolean verifyPassword(String inputPassword, String storedHashedPassword) {
    String hashedInputPassword = hashPassword(inputPassword);
    assert hashedInputPassword != null;
    return hashedInputPassword.equals(storedHashedPassword);
  }

  /**
   * 使用 SHA-256 算法對密碼進行雜湊處理.
   *
   * @param password 要雜湊的密碼
   * @return 雜湊後的密碼字串
   */
  private  String hashPassword(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(password.getBytes());
      return Base64.getEncoder().encodeToString(hashBytes);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }
}
