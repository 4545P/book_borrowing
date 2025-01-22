package com.example.book_borrowing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * JI.
 * 借閱記錄
 */
@Component
@Entity
@Table(name = "borrowing_record")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowingRecord {

  @EmbeddedId
  private BorrowingRecordId id;

  @Column(name = "borrowing_time")
  private LocalDateTime borrowingTime;

  @Column(name = "return_time")
  private LocalDateTime returnTime;

  public BorrowingRecord() {

  }

  /**
   * 帶參構造函數.
   *
   * @param id 借閱記錄ID，包含複合鍵
   * @param borrowingTime 借閱時間
   * @param returnTime 歸還時間
   */
  public BorrowingRecord(BorrowingRecordId id, LocalDateTime borrowingTime, LocalDateTime returnTime) {
    this.id = id;
    this.borrowingTime = borrowingTime;
    this.returnTime = returnTime;
  }

  public BorrowingRecordId getId() {
    return id;
  }

  public void setId(BorrowingRecordId id) {
    this.id = id;
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
