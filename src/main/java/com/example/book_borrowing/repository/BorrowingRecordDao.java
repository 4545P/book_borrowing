package com.example.book_borrowing.repository;

import com.example.book_borrowing.entity.BorrowingRecord;
import com.example.book_borrowing.entity.BorrowingRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordDao extends JpaRepository<BorrowingRecord, BorrowingRecordId> {

}
