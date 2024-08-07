package com.example.book_borrowing.vo.request;

import java.time.LocalDateTime;

/**
 * JI.
 * 表示借閱記錄請求的類，用於接收借閱記錄相關的請求數據
 */
public class BorrowingRecordRequest {

  public Integer userId;

  public Integer inventoryId;

  public LocalDateTime borrowingTime;

  public LocalDateTime returnTime;

  public BorrowingRecordRequest() {

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

  public LocalDateTime getBorrowingTime() {
    return borrowingTime;
  }

  public void setBorrowingTime(LocalDateTime borrowingTime) {
    this.borrowingTime = borrowingTime;
  }

  public LocalDateTime getReturnTime() {
    return returnTime;
  }

  public void setReturnTime(LocalDateTime returnTime) {
    this.returnTime = returnTime;
  }
}
