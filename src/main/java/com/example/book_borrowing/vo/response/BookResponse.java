package com.example.book_borrowing.vo.response;

import com.example.book_borrowing.entity.Book;

import java.util.List;

public class BookResponse {

    public List<Book> bookList;

    public String message;

    public BookResponse(List<Book> bookList, String message) {
        this.bookList = bookList;
        this.message = message;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
