package com.example.book_borrowing.repository;

import com.example.book_borrowing.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordDao extends JpaRepository<BorrowingRecord, String> {
}
