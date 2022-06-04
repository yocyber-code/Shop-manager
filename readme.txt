Need mysql-connector-java

********************************
********************************

Need database name shoeshop

********************************
********************************

Need table name product

CREATE TABLE `shoeshop`.`product` ( `product_id` INT NOT NULL AUTO_INCREMENT , 
`product_name` VARCHAR(50) NOT NULL , `price_per_unit` FLOAT NOT NULL , `product_description` TEXT NOT NULL , 
`product_image` BLOB NOT NULL , PRIMARY KEY (`product_id`)) ENGINE = InnoDB;

********************************
********************************

Need table name customer

CREATE TABLE `shoeshop`.`customer` ( `id` INT NULL , `name` VARCHAR(50) NOT NULL , `surname` VARCHAR(50) NOT NULL , 
`phone` VARCHAR(10) NOT NULL ) ENGINE = InnoDB;
