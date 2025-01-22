package com.example.book_borrowing.repository;

import com.example.book_borrowing.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JI.
 * 資料訪問層接口，用於操作用戶信息
 */
@Repository
public interface UsersDao extends JpaRepository<Users, String> {

  List<Users> findByName(String name);
}
