package com.example.book_borrowing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 * JI.
 * 嵌入式ID類，表示借閱記錄的複合鍵
 */
@Embeddable
public class BorrowingRecordId implements Serializable {

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "inventory_id")
  private Integer inventoryId;

  public BorrowingRecordId() {

  }

  /**
   * 帶參構造函數.
   *
   * @param userId 用戶ID
   * @param inventoryId 庫存ID
   */
  public BorrowingRecordId(Integer userId, Integer inventoryId) {
    this.userId = userId;
    this.inventoryId = inventoryId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getInventoryId() {
    return inventoryId;
  }

  public void setInventoryId(Integer inventoryId) {
    this.inventoryId = inventoryId;
  }
}
