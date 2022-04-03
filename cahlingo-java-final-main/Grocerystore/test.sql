
create table Groceries (
FOOD_ID INT NOT NULL AUTO_INCREMENT,
FOOD_NAME varchar(32) NOT NULL, 
PRICE numeric(10,2) NOT NULL,  
PRIMARY KEY (FOOD_ID)
);

create table Customers (
Customer_ID INT NOT NULL AUTO_INCREMENT,
Firstname varchar(32) NOT NULL,
LastName varchar(32) NOT NULL, 
Adress varchar(32) NOT NULL,
PhoneNumber INT NOT NULL,
PRIMARY KEY (Customer_ID)
);

create table Orders (
Order_ID INT NOT NULL AUTO_INCREMENT,
OrderDate DATETIME NOT NULL,
Customer_ID int NOT NULL, 
Total int NOT NULL,
PRIMARY KEY (Order_ID),
CONSTRAINT FK_CustomerOrders FOREIGN KEY (Customer_ID)
REFERENCES Customers(Customer_ID)
);

INSERT INTO Customers (Firstname,LastName,Adress,PhoneNumber) 
VALUES ('Dennis','Cahling','Testway',0709418019);

INSERT INTO Customers (Firstname,LastName,Adress,PhoneNumber) 
VALUES ('Test','Testson','NewWayay',07044444);

INSERT INTO Customers (Firstname,LastName,Adress,PhoneNumber) 
VALUES ('Roffe','Andersson','OldWay',0709418018);


INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Apple',3);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Entrecote',200);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Corn Flakes',30);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Gorbys Pirog',11);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Vanilla Icecream',30);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('NOCCO',25);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Celsius',20);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Cookies',50);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Vanilla Bun',10);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Flour',30);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Minced Meat',150);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Spaghetti',45);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Caviar',75);
INSERT INTO Groceries (FOOD_NAME,PRICE) 
VALUES ('Milk',35);

