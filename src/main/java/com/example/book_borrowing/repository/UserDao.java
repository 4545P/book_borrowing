package com.example.book_borrowing.repository;

import com.example.book_borrowing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    public User findByPhone(String phone);
}
