package com.example.book_borrowing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "Borrowing_Record")
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
