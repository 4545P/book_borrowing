package com.example.book_borrowing.repository;

import com.example.book_borrowing.entity.BorrowingRecord;
import com.example.book_borrowing.entity.BorrowingRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JI.
 * 資料訪問層接口，用於操作借閱記錄信息
 */
@Repository
public interface BorrowingRecordDao extends JpaRepository<BorrowingRecord, BorrowingRecordId> {

}
