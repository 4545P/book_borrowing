package com.example.book_borrowing.service.impl;

import com.example.book_borrowing.entity.Book;
import com.example.book_borrowing.repository.BookDao;
import com.example.book_borrowing.service.ifs.BookService;
import com.example.book_borrowing.vo.response.BookResponse;
import com.example.book_borrowing.vo.response.BorrowingRecordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Collections;

@EnableScheduling
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public BookResponse addBook(Book book) {

        if(book.getIsbn().isBlank() || book.getName().isBlank() || book.getAuthor().isBlank() || book.getIntroduction().isBlank()) {
            return new BookResponse(Collections.singletonList(book), "新增書籍失敗");
        }

        bookDao.save(book);
        return new BookResponse(Collections.singletonList(book), "新增書籍成功");
    }

    @Override
    public BorrowingRecordResponse borrowingBook(Integer userId, Integer inventoryId) {
        return null;
    }

    @Override
    public BorrowingRecordResponse returnBook(Integer userId, Integer inventoryId) {
        return null;
    }

}
