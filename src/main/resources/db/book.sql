-- 創建 book 表
CREATE TABLE IF NOT EXISTS book_borrowing."book" (
     "isbn" varchar(45) NOT NULL,
     "name" varchar(45) NOT NULL,
     "author" varchar(45) NOT NULL,
     "introduction" varchar(45) NOT NULL,
     PRIMARY KEY ("isbn")
);

-- 創建 inventory 表
CREATE TABLE IF NOT EXISTS book_borrowing."inventory" (
      "inventory_id" serial NOT NULL,
      "isbn" varchar(45) NOT NULL,
      "name" varchar(45) NOT NULL,
      "store" timestamp NOT NULL,
      "status" varchar(45) NOT NULL,
      PRIMARY KEY ("inventory_id")
);

-- 創建 users 表
CREATE TABLE IF NOT EXISTS book_borrowing."users" (
      "user_id" serial NOT NULL,
      "phone_number" varchar(45) NOT NULL,
      "user_name" varchar(45) NOT NULL,
      "password" varchar(45) NOT NULL,
      "registration" timestamp NOT NULL,
      "last_login" timestamp DEFAULT NULL,
      PRIMARY KEY ("user_id")
);

-- 創建 borrowing_record 表
CREATE TABLE IF NOT EXISTS book_borrowing."borrowing_record" (
     "user_id" int NOT NULL,
     "inventory_id" int NOT NULL,
     "borrowing_time" timestamp DEFAULT NULL,
     "return_time" timestamp DEFAULT NULL,
     PRIMARY KEY ("user_id", "inventory_id"),
     CONSTRAINT fk_inventory_inventory_id FOREIGN KEY ("inventory_id") REFERENCES book_borrowing."inventory" ("inventory_id"),
     CONSTRAINT fk_users_user_id FOREIGN KEY ("user_id") REFERENCES book_borrowing."users" ("user_id")
);