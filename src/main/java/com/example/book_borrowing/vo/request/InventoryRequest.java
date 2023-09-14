package com.example.book_borrowing.vo.request;

import java.time.LocalDateTime;

public class InventoryRequest {

    public Integer inventoryId;

    public String isbn;

    public String name;

    public LocalDateTime store;

    public String status;

    public InventoryRequest() {
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
