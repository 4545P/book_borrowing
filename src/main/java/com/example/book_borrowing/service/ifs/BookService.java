package com.example.book_borrowing.service.ifs;

import com.example.book_borrowing.entity.Book;
import com.example.book_borrowing.vo.response.BookResponse;

import java.util.Map;

public interface BookService {

    public Map<String, Object> getBook(String name);

    public BookResponse addBook(Book book);

    public BookResponse borrowingBook(Integer userId, Integer inventoryId);

    public BookResponse returnBook(Integer userId, Integer inventoryId);

    public BookResponse stock(Integer inventoryId);

    public BookResponse lost(Integer inventoryId);

    public BookResponse damaged(Integer inventoryId);

    public BookResponse scrap(Integer inventoryId);
}
