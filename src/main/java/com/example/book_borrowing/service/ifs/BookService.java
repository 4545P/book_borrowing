package com.example.book_borrowing.service.ifs;

import com.example.book_borrowing.entity.Book;
import com.example.book_borrowing.vo.response.BookResponse;
import java.util.Map;

/**
 * JI.
 * 提供圖書相關的服務接口。
 */
public interface BookService {

  Map<String, Object> getBook(String name);

  BookResponse addBook(Book book);

  BookResponse borrowingBook(Integer userId, Integer inventoryId);

  BookResponse returnBook(Integer userId, Integer inventoryId);

  BookResponse stock(Integer inventoryId);

  BookResponse lost(Integer inventoryId);

  BookResponse damaged(Integer inventoryId);

  BookResponse scrap(Integer inventoryId);
}
