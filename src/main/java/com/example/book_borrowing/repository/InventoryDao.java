package com.example.book_borrowing.repository;

import com.example.book_borrowing.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDao extends JpaRepository<Inventory, String> {
}
