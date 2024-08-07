package com.example.book_borrowing.controller;

import com.example.book_borrowing.entity.Book;
import com.example.book_borrowing.service.ifs.BookService;
import com.example.book_borrowing.vo.request.BookRequest;
import com.example.book_borrowing.vo.request.BorrowingRecordRequest;
import com.example.book_borrowing.vo.response.BookResponse;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JI.
 * 控制器類，負責處理與書籍相關的API請求
 */
@CrossOrigin
@RestController
public class BookController {


  private BookService bookService;

  @Autowired
  public void setBookService(BookService bookService) {
    this.bookService = bookService;
  }

  /**
   * 獲取書籍信息.
   *
   * @param bookRequest 包含書籍名稱的請求對象
   * @return 返回書籍信息的Map對象
   */
  @PostMapping("/api/getBook")
  public Map<String, Object> getBook(@RequestBody BookRequest bookRequest) {
    return bookService.getBook(bookRequest.getName());
  }


  /**
   * 添加新書籍.
   *
   * @param book 包含書籍信息的請求對象
   * @return 返回包含書籍添加結果的響應對象
   */
  @PostMapping("/api/book/add")
  public BookResponse addBook(@RequestBody Book book) {
    return  bookService.addBook(book);
  }

  /**
   * 借閱書籍.
   *
   * @param borrowingRecordRequest 包含用戶ID和庫存ID的請求對象
   * @return 返回包含書籍借閱結果的響應對象
   */
  @PostMapping("/api/book/borrowing")
  public BookResponse borrowingBook(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
    return  bookService.borrowingBook(borrowingRecordRequest.getUserId(), borrowingRecordRequest.getInventoryId());
  }

  /**
   * 歸還書籍.
   *
   * @param borrowingRecordRequest 包含用戶ID和庫存ID的請求對象
   * @return 返回包含書籍歸還結果的響應對象
   */
  @PostMapping("/api/book/return")
  public BookResponse returnBook(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
    return  bookService.returnBook(borrowingRecordRequest.getUserId(), borrowingRecordRequest.getInventoryId());
  }

  /**
   * 書籍入庫.
   *
   * @param borrowingRecordRequest 包含庫存ID的請求對象
   * @return 返回包含書籍入庫結果的響應對象
   */
  @PostMapping("/api/book/stock")
  public BookResponse stock(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
    return  bookService.stock(borrowingRecordRequest.getUserId(), borrowingRecordRequest.getInventoryId());
  }

  /**
   * 書籍丟失.
   *
   * @param borrowingRecordRequest 包含庫存ID的請求對象
   * @return 返回包含書籍丟失結果的響應對象
   */
  @PostMapping("/api/book/lost")
  public BookResponse lost(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
    return  bookService.lost(borrowingRecordRequest.getUserId(), borrowingRecordRequest.getInventoryId());
  }

  /**
   * 書籍損壞.
   *
   * @param borrowingRecordRequest 包含庫存ID的請求對象
   * @return 返回包含書籍損壞結果的響應對象
   */
  @PostMapping("/api/book/damaged")
  public BookResponse damaged(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
    return  bookService.damaged(borrowingRecordRequest.getUserId(), borrowingRecordRequest.getInventoryId());
  }

  /**
   * 書籍報廢.
   *
   * @param borrowingRecordRequest 包含庫存ID的請求對象
   * @return 返回包含書籍報廢結果的響應對象
   */
  @PostMapping("/api/book/scrap")
  public BookResponse scrap(@RequestBody BorrowingRecordRequest borrowingRecordRequest) {
    return  bookService.scrap(borrowingRecordRequest.getUserId(), borrowingRecordRequest.getInventoryId());
  }
}
