package com.example.book_borrowing.repository;

import com.example.book_borrowing.entity.Inventory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JI.
 * 資料訪問層接口，用於操作庫存信息
 */
@Repository
public interface InventoryDao extends JpaRepository<Inventory, Integer> {

  /**
   * 根據書籍名稱的部分內容進行模糊查詢，忽略大小寫.
   *
   * @param name 書籍名稱的部分內容
   * @return 符合條件的庫存信息列表
   */
  List<Inventory> findAllByNameContainingIgnoreCase(String name);
}
