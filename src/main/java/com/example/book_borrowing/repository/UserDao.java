package com.example.book_borrowing.repository;

import com.example.book_borrowing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JI.
 * 資料訪問層接口，用於操作用戶信息
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {

  /**
   * 根據電話號碼查詢用戶.
   *
   * @param phone 電話號碼
   * @return 符合條件的用戶，如果找不到則返回 null
   */
  User findByPhone(String phone);
}
