package com.example.book_borrowing.service.impl;

import com.example.book_borrowing.constants.WidgetApiRtnCode;
import com.example.book_borrowing.entity.Book;
import com.example.book_borrowing.entity.BorrowingRecord;
import com.example.book_borrowing.entity.BorrowingRecordId;
import com.example.book_borrowing.entity.Inventory;
import com.example.book_borrowing.repository.BookDao;
import com.example.book_borrowing.repository.BorrowingRecordDao;
import com.example.book_borrowing.repository.InventoryDao;
import com.example.book_borrowing.service.ifs.BookService;
import com.example.book_borrowing.vo.response.BookResponse;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * JI.
 * 接口的實現類，提供與書籍相關的操作功能
 * @author blue
 */
@EnableScheduling
@Service
public class BookServiceImpl implements BookService {

  private final BookDao bookDao;

  private final InventoryDao inventoryDao;

  private final BorrowingRecordDao borrowingRecordDao;

  @Autowired
  public BookServiceImpl(
      BookDao bookDao, InventoryDao inventoryDao, BorrowingRecordDao borrowingRecordDao) {
    this.bookDao = bookDao;
    this.inventoryDao = inventoryDao;
    this.borrowingRecordDao = borrowingRecordDao;
  }

  @Override
  public Map<String, Object> getBook(@RequestParam(required = false) String name) {
    List<Inventory> inventoryList;
    long totalElements;
    if (name != null && !name.isEmpty()) {
      inventoryList = inventoryDao.findAllByNameContainingIgnoreCase(name);
    } else {
      // 如果沒有參數則返回全部
      inventoryList = inventoryDao.findAll();
    }
    // 計算總數
    totalElements = inventoryList.size();
    List<Map<String, Object>> resultList = getMapInventory(inventoryList);
    Map<String, Object> result = new HashMap<>();
    result.put("list", resultList);
    result.put("total", totalElements);
    return result;
  }

  private static List<Map<String, Object>> getMapInventory(List<Inventory> inventoryList) {
    List<Map<String, Object>> responses = new ArrayList<>();
    for (Inventory inventory : inventoryList) {
      Map<String, Object> response = new HashMap<>();
      response.put("inventoryId", inventory.getInventoryId());
      response.put("isbn", inventory.getIsbn());
      response.put("name", inventory.getName());
      response.put("status", inventory.getStatus());
      responses.add(response);
    }
    return responses;
  }

  @Override
  @Transactional
  public BookResponse addBook(Book book) {
    // 驗證輸入參數是否有效
    if (book.getIsbn().isBlank()
      || book.getName().isBlank()
      || book.getAuthor().isBlank()
      || book.getIntroduction().isBlank()) {
      return new BookResponse(Collections.singletonList(book), WidgetApiRtnCode.PARANETER_REQUIRE.getMessage());
    } else {
      // 保存書籍資料到 book 表
      bookDao.save(book);

      // 創建並保存對應的 inventory 資料
      Inventory inventory = new Inventory();
      inventory.setIsbn(book.getIsbn());
      inventory.setName(book.getName());
      LocalDateTime storeTime = LocalDateTime.now();
      inventory.setStore(storeTime);
      inventory.setStatus(WidgetApiRtnCode.STOCK.getMessage());
      inventoryDao.save(inventory);
    }
    // 返回成功的響應
    return new BookResponse(Collections.singletonList(book), WidgetApiRtnCode.SUCCESSFUL.getMessage());
  }

  @Override
  @Transactional
  public BookResponse borrowingBook(Integer userId, Integer inventoryId) {
    if (userId == null || inventoryId == null) {
      return new BookResponse(WidgetApiRtnCode.FAILED.getMessage());
    }
    Optional<Inventory> inventoryOptional = inventoryDao.findById(inventoryId);
    if (inventoryOptional.isEmpty()) {
      return new BookResponse(WidgetApiRtnCode.NOT_FOUND.getMessage());
    }
    Inventory inventory = inventoryOptional.get();
    if (!WidgetApiRtnCode.STOCK.getMessage().equals(inventory.getStatus())) {
      return new BookResponse(WidgetApiRtnCode.BORROW_FAIL.getMessage());
    }
    BorrowingRecord borrowingRecord = new BorrowingRecord();
    BorrowingRecordId id = new BorrowingRecordId(userId, inventoryId);
    borrowingRecord.setId(id);
    borrowingRecord.setBorrowingTime(LocalDateTime.now());
    borrowingRecord.setReturnTime(null);
    borrowingRecordDao.save(borrowingRecord);
    inventory.setStatus(WidgetApiRtnCode.BORROWED.getMessage());
    inventoryDao.save(inventory);
    return new BookResponse(WidgetApiRtnCode.BORROW_SUCCESS.getMessage());
  }

  @Override
  @Transactional
  public BookResponse returnBook(Integer userId, Integer inventoryId) {
    if (userId == null || inventoryId == null) {
      return new BookResponse(WidgetApiRtnCode.FAILED.getMessage());
    }
    Optional<BorrowingRecord> borrowingRecordOptional = borrowingRecordDao.findById(new BorrowingRecordId(userId, inventoryId));
    if (borrowingRecordOptional.isEmpty()) {
      return new BookResponse(WidgetApiRtnCode.NO_RECORD.getMessage());
    }
    BorrowingRecord borrowingRecord = borrowingRecordOptional.get();
    borrowingRecord.setReturnTime(LocalDateTime.now());
    Optional<Inventory> inventoryOptional = inventoryDao.findById(inventoryId);
    if (inventoryOptional.isEmpty()) {
      return new BookResponse(WidgetApiRtnCode.NOT_FOUND.getMessage());
    }
    Inventory inventory = inventoryOptional.get();
    if (!WidgetApiRtnCode.BORROWED.getMessage().equals(inventory.getStatus())) {
      return new BookResponse(WidgetApiRtnCode.RETURN_FAIL.getMessage());
    }
    inventory.setStatus(WidgetApiRtnCode.IN_ORGANIZATION.getMessage());
    inventoryDao.save(inventory);
    return new BookResponse(WidgetApiRtnCode.RETURN_SUCCESS.getMessage());
  }

  @Override
  public BookResponse stock(Integer userId, Integer inventoryId) {
    if (userId == null || inventoryId == null) {
      return new BookResponse(WidgetApiRtnCode.FAILED.getMessage());
    }
    Optional<Inventory> inventoryOptional = inventoryDao.findById(inventoryId);
    if (inventoryOptional.isPresent()) {
      Inventory inventory = inventoryOptional.get();
      if (!WidgetApiRtnCode.IN_ORGANIZATION.getMessage().equals(inventory.getStatus()) || !WidgetApiRtnCode.LOST.getMessage().equals(inventory.getStatus())) {
        return new BookResponse(WidgetApiRtnCode.UPDATE_FAIL.getMessage());
      }
      inventory.setStatus(WidgetApiRtnCode.STOCK.getMessage());
      inventoryDao.save(inventory);
      return new BookResponse(WidgetApiRtnCode.STOCK_IN.getMessage());
    } else {
      return new BookResponse(WidgetApiRtnCode.NOT_FOUND.getMessage());
    }
  }

  @Override
  public BookResponse lost(Integer userId, Integer inventoryId) {
    return updateInventoryStatus(userId, inventoryId, "遺失");
  }

  @Override
  public BookResponse damaged(Integer userId, Integer inventoryId) {
    return updateInventoryStatus(userId, inventoryId, "損毀");
  }

  @Override
  public BookResponse scrap(Integer userId, Integer inventoryId) {
    return updateInventoryStatus(userId, inventoryId, "報廢");
  }

  private BookResponse updateInventoryStatus(Integer userId, Integer inventoryId, String status) {
    if (userId == null || inventoryId == null) {
      return new BookResponse(WidgetApiRtnCode.FAILED.getMessage());
    }
    Optional<Inventory> inventoryOptional = inventoryDao.findById(inventoryId);
    if (inventoryOptional.isPresent()) {
      Inventory inventory = inventoryOptional.get();
      inventory.setStatus(status);
      inventoryDao.save(inventory);
      return new BookResponse(status);
    } else {
      return new BookResponse(WidgetApiRtnCode.NOT_FOUND.getMessage());
    }
  }
}
