package com.example.book_borrowing.repository;

import com.example.book_borrowing.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JI.
 * 資料訪問層接口，用於操作書籍信息
 */
@Repository
public interface BookDao extends JpaRepository<Book, String> {

}
