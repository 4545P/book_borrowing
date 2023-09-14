package com.example.book_borrowing.controller;


import com.example.book_borrowing.entity.Book;
import com.example.book_borrowing.service.ifs.BookService;
import com.example.book_borrowing.vo.request.BookRequest;
import com.example.book_borrowing.vo.request.BorrowingRecordRequest;
import com.example.book_borrowing.vo.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/api/getBook")
    public Map<String, Object> getBook(@RequestBody BookRequest bookRequest) {
        return bookService.getBook(bookRequest.getName());
    }


    @PostMapping("/api/book/add")
    public BookResponse addBook(@RequestBody Book book) {
        return  bookService.addBook(book);
    }

    @PostMapping("/api/book/borrowing")
    public BookResponse borrowingBook(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
        return  bookService.borrowingBook(borrowingRecordRequest.getUserId(), borrowingRecordRequest.getInventoryId());
    }

    @PostMapping("/api/book/return")
    public BookResponse returnBook(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
        return  bookService.returnBook(borrowingRecordRequest.getUserId(), borrowingRecordRequest.getInventoryId());
    }

    @PostMapping("/api/book/stock")
    public BookResponse stock(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
        return  bookService.stock(borrowingRecordRequest.getInventoryId());
    }

    @PostMapping("/api/book/lost")
    public BookResponse lost(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
        return  bookService.lost(borrowingRecordRequest.getInventoryId());
    }

    @PostMapping("/api/book/damaged")
    public BookResponse damaged(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
        return  bookService.damaged(borrowingRecordRequest.getInventoryId());
    }

    @PostMapping("/api/book/scrap")
    public BookResponse scrap(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
        return  bookService.scrap(borrowingRecordRequest.getInventoryId());
    }

}
