CREATE TABLE IF NOT EXISTS `Book` (
     `isbn` varchar(45) NOT NULL,
     `name` varchar(45) NOT NULL,
     `author` varchar(45) NOT NULL,
     `introduction` varchar(45) NOT NULL,
     PRIMARY KEY (`isbn`)
);

CREATE TABLE IF NOT EXISTS `Borrowing_Record` (
    `user_id` int NOT NULL,
     `inventory_id` int NOT NULL,
     `borrowing_time` datetime DEFAULT NULL,
     `return_time` datetime DEFAULT NULL,
     PRIMARY KEY (`user_id`,`inventory_id`),
     KEY `idx_user_id` (`user_id`),
     KEY `idx_inventory_id` (`user_id`,`inventory_id`),
     KEY `fk_inventory_id_idx` (`inventory_id`),
     CONSTRAINT `fk_inventory_id` FOREIGN KEY (`inventory_id`) REFERENCES `Inventory` (`inventory_id`),
     CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`)
);

CREATE TABLE IF NOT EXISTS `Inventory` (
     `inventory_id` int NOT NULL AUTO_INCREMENT,
     `isbn` varchar(45) NOT NULL,
     `store` datetime NOT NULL,
     `status` varchar(45) NOT NULL,
      PRIMARY KEY (`inventory_id`),
      KEY `fk_isbn_idx` (`isbn`),
      CONSTRAINT `fk_isbn` FOREIGN KEY (`isbn`) REFERENCES `Book` (`isbn`)
);

CREATE TABLE IF NOT EXISTS `User` (
     `user_id` int NOT NULL AUTO_INCREMENT,
     `phone_number` varchar(45) NOT NULL,
     `user_name` varchar(45) NOT NULL,
     `password` varchar(45) NOT NULL,
     `registration` datetime NOT NULL,
     `last_login` datetime DEFAULT NULL,
      PRIMARY KEY (`user_id`)
);