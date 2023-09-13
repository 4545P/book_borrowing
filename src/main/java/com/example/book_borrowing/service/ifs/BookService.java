package com.example.book_borrowing.service.ifs;

import com.example.book_borrowing.entity.Book;
import com.example.book_borrowing.vo.response.BookResponse;
import com.example.book_borrowing.vo.response.BorrowingRecordResponse;

public interface BookService {

    public BookResponse addBook(Book book);

    public BorrowingRecordResponse borrowingBook(Integer userId, Integer inventoryId);

    public BorrowingRecordResponse returnBook(Integer userId, Integer inventoryId);
}
