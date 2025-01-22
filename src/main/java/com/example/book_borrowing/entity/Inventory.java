package com.example.book_borrowing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * JI.
 * 庫存信息
 */
@Component
@Entity
@Table(name = "inventory")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inventory_id")
  private Integer inventoryId;

  @Column(name = "isbn")
  private String isbn;

  @Column(name = "name")
  private String name;

  @Column(name = "store")
  private LocalDateTime store;

  @Column(name = "status")
  private String status;

  public Inventory() {

  }

  /**
   * 帶參構造函數.
   *
   * @param inventoryId 庫存ID
   * @param isbn 書籍的國際標準書號
   * @param name 書籍名稱
   * @param store 入庫時間
   * @param status 庫存狀態
   */
  public Inventory(Integer inventoryId, String isbn, String name, LocalDateTime store, String status) {
    this.inventoryId = inventoryId;
    this.isbn = isbn;
    this.name = name;
    this.store = store;
    this.status = status;
  }

  public Integer getInventoryId() {
    return inventoryId;
  }

  public void setInventoryId(Integer inventoryId) {
    this.inventoryId = inventoryId;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public LocalDateTime getStore() {
    return store;
  }

  public void setStore(LocalDateTime store) {
    this.store = store;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
