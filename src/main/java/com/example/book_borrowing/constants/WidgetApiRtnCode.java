package com.example.book_borrowing.constants;

public enum WidgetApiRtnCode {
  SUCCESSFUL(200, "成功"),
  FAILED(400, "失敗"),
  PARANETER_REQUIRE(401, "缺少必要參數"),
  PHONE_ERROR(1000, "手機號碼錯誤"),
  ADD_BOOK_FAIL(1001, "新增書籍失敗"),
  ADD_BOOK_SUCCESS(1002, "新增書籍成功"),
  BORROW_FAIL(1003, "借閱失敗"),
  BORROW_SUCCESS(1004, "借閱成功"),
  RETURN_FAIL(1005, "還書失敗"),
  RETURN_SUCCESS(1006, "還書成功"),
  NO_RECORD(1007, "沒有借閱紀錄"),
  NOT_FOUND(1008, "找不到該庫存"),
  UPDATE_FAIL(1009, "書籍不可操作"),
  STOCK(10010, "在庫");

  private final int code;
  private final String message;

  WidgetApiRtnCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
