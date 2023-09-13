package com.example.book_borrowing.controller;


import com.example.book_borrowing.entity.Book;
import com.example.book_borrowing.service.ifs.BookService;
import com.example.book_borrowing.vo.request.BorrowingRecordRequest;
import com.example.book_borrowing.vo.request.InventoryRequest;
import com.example.book_borrowing.vo.response.BookResponse;
import com.example.book_borrowing.vo.response.BorrowingRecordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book/add")
    public BookResponse addBook(@RequestBody Book book) {
        return  bookService.addBook(book);
    }

    @PostMapping("/book/borrowing")
    public BorrowingRecordResponse borrowingBook(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
        return  bookService.borrowingBook(borrowingRecordRequest.getUserId(), borrowingRecordRequest.getInventoryId());
    }

    @PostMapping("/book/return")
    public BorrowingRecordResponse returnBook(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
        return  bookService.returnBook(borrowingRecordRequest.getUserId(), borrowingRecordRequest.getInventoryId());
    }

}
