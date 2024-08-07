package com.example.book_borrowing.vo.request;

/**
 * JI.
 * 表示書籍請求的類，用於接收書籍相關的請求數據
 */
public class BookRequest {

  public String isbn;

  public String name;

  public String author;

  public String introduction;

  public BookRequest() {

  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }
}
