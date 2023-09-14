package com.example.book_borrowing.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;

@EnableScheduling
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Autowired
    Inventory inventory;

    @Autowired
    InventoryDao inventoryDao;

    @Autowired
    BorrowingRecord borrowingRecord;

    @Autowired
    BorrowingRecordDao borrowingRecordDao;


    @Override
    public Map<String, Object> getBook(@RequestParam(required = false) String name) {
        List<Inventory> inventoryList;
        long totalElements;

        if (name != null && !name.isEmpty()) {
            inventoryList = inventoryDao.findAllByNameContainingIgnoreCase(name);
            totalElements = inventoryList.size(); // 計算總數
        } else {
            // 如果沒有參數則返回全部
            inventoryList = inventoryDao.findAll();
            totalElements = inventoryList.size();
        }

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

        if(book.getIsbn().isBlank() || book.getName().isBlank() || book.getAuthor().isBlank() || book.getIntroduction().isBlank()) {
            return new BookResponse(Collections.singletonList(book), "新增書籍失敗");
        } else {
            Inventory inventory = new Inventory();
            inventory.setIsbn(book.getIsbn());
            inventory.setName(book.getName());
            LocalDateTime storeTime = LocalDateTime.now();
            inventory.setStore(storeTime);
            inventory.setStatus("在庫");
            inventoryDao.save(inventory);
        }
        bookDao.save(book);
        return new BookResponse(Collections.singletonList(book), "新增書籍成功");
    }

    @Override
    @Transactional
    public BookResponse borrowingBook(Integer userId, Integer inventoryId) {

        if (userId == null || inventoryId == null) {
            return new BookResponse("借閱失敗");
        }

        Optional<Inventory> inventoryOptional = inventoryDao.findById(inventoryId);
        Inventory inventory = inventoryOptional.get();

        if (!inventory.getStatus().equals("在庫")) {
            return new BookResponse("書籍不可借閱");
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        BorrowingRecordId id = new BorrowingRecordId();
        id.setUserId(userId);
        id.setInventoryId(inventoryId);
        borrowingRecord.setId(id);
        borrowingRecord.setBorrowingTime(LocalDateTime.now());
        borrowingRecord.setReturnTime(null);
        borrowingRecordDao.save(borrowingRecord);

        inventory.setStatus("出借中");
        inventoryDao.save(inventory);

        return new BookResponse("借閱成功");
    }

    @Override
    @Transactional
    public BookResponse returnBook(Integer userId, Integer inventoryId) {

        if (userId == null || inventoryId == null) {
            return new BookResponse("還書失敗");
        }

        Optional<BorrowingRecord> borrowingRecordOptional = borrowingRecordDao.findById(new BorrowingRecordId(userId, inventoryId));
        if (!borrowingRecordOptional.isPresent()) {
            return new BookResponse("沒有借閱紀錄");
        }

        BorrowingRecord borrowingRecord = borrowingRecordOptional.get();
        borrowingRecord.setReturnTime(LocalDateTime.now());

        Optional<Inventory> inventoryOptional = inventoryDao.findById(inventoryId);
        Inventory inventory = inventoryOptional.get();
        inventory.setStatus("整理中");
        inventoryDao.save(inventory);

        return new BookResponse("還書成功");

    }

    @Override
    public BookResponse stock(Integer inventoryId) {
        Optional<Inventory> inventoryOptional = inventoryDao.findById(inventoryId);
        Inventory inventory = inventoryOptional.get();

        if (!inventory.getStatus().equals("整理中")) {
            return new BookResponse("書籍不可操作");
        }

        inventory.setStatus("在庫");
        inventoryDao.save(inventory);
        return new BookResponse("在庫");
    }

    @Override
    public BookResponse lost(Integer inventoryId) {
        Optional<Inventory> inventoryOptional = inventoryDao.findById(inventoryId);
        Inventory inventory = inventoryOptional.get();
        inventory.setStatus("遺失");
        inventoryDao.save(inventory);
        return new BookResponse("遺失");
    }

    @Override
    public BookResponse damaged(Integer inventoryId) {
        Optional<Inventory> inventoryOptional = inventoryDao.findById(inventoryId);
        Inventory inventory = inventoryOptional.get();
        inventory.setStatus("損毀");
        inventoryDao.save(inventory);
        return new BookResponse("損毀");
    }

    @Override
    public BookResponse scrap(Integer inventoryId) {
        Optional<Inventory> inventoryOptional = inventoryDao.findById(inventoryId);
        Inventory inventory = inventoryOptional.get();
        inventory.setStatus("報廢");
        inventoryDao.save(inventory);
        return new BookResponse("報廢");
    }

}
