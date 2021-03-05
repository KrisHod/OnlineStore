CREATE TABLE customer
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    doB DATE NOT NULL,
    address VARCHAR(60) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    phoneNumber VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE item
(
    id INT NOT NULL,
    title VARCHAR(50) NOT NULL,
    code INT NOT NULL,
    producer VARCHAR(50),
    dateOfLastUpdate DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id INT NOT NULL AUTO_INCREMENT,
    customerId INT NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY cust_fk (customerId) REFERENCES customer (id),
    PRIMARY KEY (id)
);

CREATE TABLE orderedItems
(
    id INT NOT NULL AUTO_INCREMENT,
    orderId INT NOT NULL,
    itemId INT NOT NULL,
    FOREIGN KEY order_fk (orderId) REFERENCES `order` (id),
    FOREIGN KEY item_fk (itemId) REFERENCES item (id),
    PRIMARY KEY (id)
);

ALTER TABLE item
    ADD COLUMN primaryItem TINYINT NOT NULL AFTER dateOfLastUpdate;

ALTER TABLE item
    ADD COLUMN candidateToRemove TINYINT NOT NULL AFTER primaryItem;
