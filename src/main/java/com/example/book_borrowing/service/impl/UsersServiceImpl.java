package com.example.book_borrowing.service.impl;

import com.example.book_borrowing.constants.WidgetApiRtnCode;
import com.example.book_borrowing.entity.Users;
import com.example.book_borrowing.repository.UsersDao;
import com.example.book_borrowing.service.ifs.UsersService;
import com.example.book_borrowing.vo.response.UsersResponse;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

/**
 * JI.
 * 用戶服務的實現類，實現了 UserService 接口，並且使用了定時任務功能
 */
@EnableScheduling
@Service
public class UsersServiceImpl implements UsersService {

  private final UsersDao userDao;

  @Autowired
  public UsersServiceImpl(UsersDao userDao) {
    this.userDao = userDao;
  }

  private static final String PHONE_PATTERN = "^09\\d{8}$";

  /**
   * 將使用者註冊到系統中，如果必要的用戶資訊不完整，返回註冊失敗回應.
   *
   * @param user 要註冊的使用者物件
   * @return 表示註冊結果的 UserResponse 物件
   */
  @Override

  public UsersResponse addUser(Users user) {
    if (!user.getPhone().matches(PHONE_PATTERN)) {
      return new UsersResponse(Collections.singletonList(user), WidgetApiRtnCode.PHONE_ERROR.getMessage());
    }
    if (user.getName().isBlank() || user.getPassword().isBlank()) {
      return new UsersResponse(Collections.singletonList(user), WidgetApiRtnCode.PARANETER_REQUIRE.getMessage());
    } else {
      LocalDateTime registerTime = LocalDateTime.now();
      user.setRegistration(registerTime);
      String hashedPassword = hashPassword(user.getPassword());
      user.setPassword(hashedPassword);
      user.setLastLogin(registerTime);
    }
    userDao.save(user);
    return new UsersResponse(Collections.singletonList(user), WidgetApiRtnCode.SUCCESSFUL.getMessage());
  }

  /**
   * 驗證用戶登錄信息是否有效。如果用戶存在且密碼驗證成功，則更新用戶的最後登錄時間並返回成功的登錄回應。
   * 如果用戶不存在或密碼錯誤，則返回登錄失敗回應。
   *
   * @param name     用戶姓名，用於查找對應的用戶
   * @param password 用戶密碼，用於驗證用戶身份
   * @return 返回表示登錄結果的 UsersResponse 物件，包含用戶信息及登錄狀態
   */
  @Override
  public UsersResponse isValidUser(String name, String password) {
    // 先防呆處理：檢查用戶名和密碼是否為空
    if (name == null || name.trim().isEmpty()) {
      return new UsersResponse(WidgetApiRtnCode.PARANETER_REQUIRE.getMessage());
    }
    if (password == null || password.trim().isEmpty()) {
      return new UsersResponse(WidgetApiRtnCode.PASSWORD_ERROR.getMessage());
    }

    List<Users> usersList = userDao.findByName(name);

    if (usersList != null && !usersList.isEmpty()) {
      for (Users user : usersList) {
        String storedHashedPassword = user.getPassword();
        if (verifyPassword(password, storedHashedPassword)) {
          // 密碼驗證成功，更新最後登錄時間
          LocalDateTime lastLoginTime = LocalDateTime.now();
          user.setLastLogin(lastLoginTime);
          userDao.save(user);
          return new UsersResponse(user.getUserId(), user.getPhone(), user.getName(), WidgetApiRtnCode.SUCCESSFUL.getMessage());
        }
      }

      return new UsersResponse(WidgetApiRtnCode.PASSWORD_ERROR.getMessage());
    }

    return new UsersResponse(WidgetApiRtnCode.USER_NOT_FOUND.getMessage());
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
    if (hashedInputPassword == null) {
      return false;
    }
    return hashedInputPassword.equals(storedHashedPassword);
  }

  /**
   * 使用 SHA-256 算法對提供的密碼進行雜湊處理，並將結果轉換為 Base64 編碼的字串。
   * 此方法用於將明文密碼轉換為雜湊形式，以便進行安全存儲或比對。
   *
   * @param password 要雜湊的明文密碼
   * @return 雜湊後的密碼字串（Base64 編碼），如果雜湊過程失敗，則返回 null
   */
  public String hashPassword(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(hashBytes);
    } catch (NoSuchAlgorithmException e) {
      System.out.println("Password hashing failed: " + e);
      return null;
    }
  }
}
