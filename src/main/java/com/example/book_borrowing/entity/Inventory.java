package com.example.book_borrowing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "Inventory")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "store")
    private LocalDateTime store;

    @Column(name = "status")
    private String status;

    public Inventory() {
    }

    public Inventory(Integer inventoryId, String isbn, LocalDateTime store, String status) {
        this.inventoryId = inventoryId;
        this.isbn = isbn;
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
}
