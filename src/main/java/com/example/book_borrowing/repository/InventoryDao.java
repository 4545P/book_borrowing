package com.example.book_borrowing.repository;

import com.example.book_borrowing.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryDao extends JpaRepository<Inventory, Integer> {
   public List<Inventory> findAllByNameContainingIgnoreCase(String name);
}
