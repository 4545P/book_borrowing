package com.example.book_borrowing.vo.response;

import com.example.book_borrowing.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {

    public List<Book> bookList;

    public String message;

    public BookResponse(String message) {
        this.message = message;
    }

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
