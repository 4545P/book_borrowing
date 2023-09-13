package com.example.book_borrowing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BorrowingRecordId implements Serializable {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "inventory_id")
    private Integer inventoryId;

    public BorrowingRecordId() {
    }

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
