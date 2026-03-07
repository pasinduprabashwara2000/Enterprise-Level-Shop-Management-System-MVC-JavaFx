CREATE DATABASE IF NOT EXISTS shop_management;
USE shop_management;

-- =========================
-- CATEGORY
-- =========================
CREATE TABLE Category (
                          categoryID VARCHAR(10) PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT
);

DELIMITER $$

CREATE TRIGGER trg_before_insert_category
    BEFORE INSERT ON Category
    FOR EACH ROW
BEGIN
    DECLARE v_max INT DEFAULT 0;
    DECLARE v_next INT;

    SELECT COALESCE(MAX(CAST(SUBSTRING(categoryID,4) AS UNSIGNED)),0)
    INTO v_max
    FROM Category
    WHERE categoryID REGEXP '^CAT[0-9]+$';

    SET v_next = v_max + 1;
    SET NEW.categoryID = CONCAT('CAT',LPAD(v_next,3,'0'));
END$$

    DELIMITER ;

-- =========================
-- CUSTOMER
-- =========================
    CREATE TABLE Customer (
                              customerId VARCHAR(10) PRIMARY KEY,
                              name VARCHAR(100) NOT NULL,
                              phone BIGINT,
                              email VARCHAR(100),
                              loyaltyCode VARCHAR(50)
    );

    DELIMITER $$

    CREATE TRIGGER trg_before_insert_customer
        BEFORE INSERT ON Customer
        FOR EACH ROW
    BEGIN
        DECLARE v_max INT DEFAULT 0;
    DECLARE v_next INT;

        SELECT COALESCE(MAX(CAST(SUBSTRING(customerId,2) AS UNSIGNED)),0)
        INTO v_max
        FROM Customer
        WHERE customerId REGEXP '^C[0-9]+$';

        SET v_next = v_max + 1;
    SET NEW.customerId = CONCAT('C',LPAD(v_next,3,'0'));
END$$

        DELIMITER ;

-- =========================
-- SUPPLIER
-- =========================
        CREATE TABLE Supplier (
                                  supplierID VARCHAR(10) PRIMARY KEY,
                                  name VARCHAR(100) NOT NULL,
                                  contactPerson VARCHAR(100),
                                  phone VARCHAR(20),
                                  email VARCHAR(100),
                                  address TEXT
        );

        DELIMITER $$

        CREATE TRIGGER trg_before_insert_supplier
            BEFORE INSERT ON Supplier
            FOR EACH ROW
        BEGIN
            DECLARE v_max INT DEFAULT 0;
    DECLARE v_next INT;

            SELECT COALESCE(MAX(CAST(SUBSTRING(supplierID,4) AS UNSIGNED)),0)
            INTO v_max
            FROM Supplier
            WHERE supplierID REGEXP '^SUP[0-9]+$';

            SET v_next = v_max + 1;
    SET NEW.supplierID = CONCAT('SUP',LPAD(v_next,3,'0'));
END$$

            DELIMITER ;

-- =========================
-- PRODUCT
-- =========================
            CREATE TABLE Product (
                                     productID VARCHAR(10) PRIMARY KEY,
                                     SKU VARCHAR(100) UNIQUE NOT NULL,
                                     barCode BIGINT,
                                     name VARCHAR(150) NOT NULL,
                                     unitPrice DECIMAL(10,2) NOT NULL,
                                     qty INT NOT NULL DEFAULT 0,
                                     active TINYINT(1) DEFAULT 1 NOT NULL,
                                     categoryID VARCHAR(10),
                                     FOREIGN KEY (categoryID) REFERENCES Category(categoryID)
            );

            DELIMITER $$

            CREATE TRIGGER trg_before_insert_product
                BEFORE INSERT ON Product
                FOR EACH ROW
            BEGIN
                DECLARE v_max INT DEFAULT 0;
    DECLARE v_next INT;

                SELECT COALESCE(MAX(CAST(SUBSTRING(productID,2) AS UNSIGNED)),0)
                INTO v_max
                FROM Product
                WHERE productID REGEXP '^P[0-9]+$';

                SET v_next = v_max + 1;
    SET NEW.productID = CONCAT('P',LPAD(v_next,3,'0'));
END$$

                DELIMITER ;

-- =========================
-- ORDERS
-- =========================
                CREATE TABLE orders (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        date DATE NOT NULL,
                                        customer_id VARCHAR(10),
                                        FOREIGN KEY (customer_id) REFERENCES Customer(customerId)
                );

                CREATE TABLE order_products (
                                                id INT AUTO_INCREMENT PRIMARY KEY,
                                                order_id INT NOT NULL,
                                                product_id VARCHAR(10) NOT NULL,
                                                qty INT NOT NULL,
                                                price DECIMAL(10,2) NOT NULL,
                                                FOREIGN KEY (order_id) REFERENCES orders(id),
                                                FOREIGN KEY (product_id) REFERENCES Product(productID)
                );

                -- =========================
-- PAYMENT
-- =========================
                CREATE TABLE Payment (
                                         payment_id VARCHAR(10) PRIMARY KEY,
                                         customer_id VARCHAR(10),
                                         method VARCHAR(50),
                                         amount DECIMAL(10,2) NOT NULL,
                                         reference VARCHAR(100),
                                         received_at DATE NOT NULL,
                                         FOREIGN KEY (customer_id) REFERENCES Customer(customerId)
                );

                DELIMITER $$

                CREATE TRIGGER trg_before_insert_payment
                    BEFORE INSERT ON Payment
                    FOR EACH ROW
                BEGIN
                    DECLARE v_max INT DEFAULT 0;
    DECLARE v_next INT;

                    SELECT COALESCE(MAX(CAST(SUBSTRING(payment_id,4) AS UNSIGNED)),0)
                    INTO v_max
                    FROM Payment
                    WHERE payment_id REGEXP '^PAY[0-9]+$';

                    SET v_next = v_max + 1;
    SET NEW.payment_id = CONCAT('PAY',LPAD(v_next,3,'0'));
END$$

                    DELIMITER ;

-- =========================
-- RETURNS
-- =========================
                    CREATE TABLE Returns (
                                             returnId VARCHAR(10) PRIMARY KEY,
                                             paymentId VARCHAR(10),
                                             action VARCHAR(50),
                                             reason TEXT,
                                             refundAmount DECIMAL(10,2),
                                             returnDate DATE NOT NULL,
                                             status VARCHAR(50),
                                             FOREIGN KEY (paymentId) REFERENCES Payment(payment_id)
                    );

                    DELIMITER $$

                    CREATE TRIGGER trg_before_insert_returns
                        BEFORE INSERT ON Returns
                        FOR EACH ROW
                    BEGIN
                        DECLARE v_max INT DEFAULT 0;
    DECLARE v_next INT;

                        SELECT COALESCE(MAX(CAST(SUBSTRING(returnId,4) AS UNSIGNED)),0)
                        INTO v_max
                        FROM Returns
                        WHERE returnId REGEXP '^RET[0-9]+$';

                        SET v_next = v_max + 1;
    SET NEW.returnId = CONCAT('RET',LPAD(v_next,3,'0'));
END$$

                        DELIMITER ;

-- =========================
-- PURCHASE ORDER
-- =========================
                        CREATE TABLE purchase_order (
                                                        po_id VARCHAR(10) PRIMARY KEY,
                                                        supplier_id VARCHAR(10),
                                                        total_cost DECIMAL(10,2) NOT NULL,
                                                        status VARCHAR(50),
                                                        create_at DATE NOT NULL,
                                                        expected_date DATE,
                                                        FOREIGN KEY (supplier_id) REFERENCES Supplier(supplierID)
                        );

                        DELIMITER $$

                        CREATE TRIGGER trg_before_insert_purchase_order
                            BEFORE INSERT ON purchase_order
                            FOR EACH ROW
                        BEGIN
                            DECLARE v_max INT DEFAULT 0;
    DECLARE v_next INT;

                            SELECT COALESCE(MAX(CAST(SUBSTRING(po_id,3) AS UNSIGNED)),0)
                            INTO v_max
                            FROM purchase_order
                            WHERE po_id REGEXP '^PO[0-9]+$';

                            SET v_next = v_max + 1;
    SET NEW.po_id = CONCAT('PO',LPAD(v_next,3,'0'));
END$$

                            DELIMITER ;

-- =========================
-- EXPENSE
-- =========================
                            CREATE TABLE expense (
                                                     id VARCHAR(10) PRIMARY KEY,
                                                     title VARCHAR(150) NOT NULL,
                                                     category_type VARCHAR(100),
                                                     amount DECIMAL(10,2) NOT NULL,
                                                     payment_method VARCHAR(50),
                                                     date DATE NOT NULL
                            );

                            DELIMITER $$

                            CREATE TRIGGER trg_before_insert_expense
                                BEFORE INSERT ON expense
                                FOR EACH ROW
                            BEGIN
                                DECLARE v_max INT DEFAULT 0;
    DECLARE v_next INT;

                                SELECT COALESCE(MAX(CAST(SUBSTRING(id,4) AS UNSIGNED)),0)
                                INTO v_max
                                FROM expense
                                WHERE id REGEXP '^EXP[0-9]+$';

                                SET v_next = v_max + 1;
    SET NEW.id = CONCAT('EXP',LPAD(v_next,3,'0'));
END$$

                                DELIMITER ;

-- =========================
-- USER
-- =========================
                                CREATE TABLE User (
                                                      userID VARCHAR(10) PRIMARY KEY,
                                                      userName VARCHAR(100) NOT NULL,
                                                      password VARCHAR(255) NOT NULL,
                                                      active VARCHAR(25),
                                                      createdAt DATE
                                );

                                DELIMITER $$

                                CREATE TRIGGER trg_before_insert_user
                                    BEFORE INSERT ON User
                                    FOR EACH ROW
                                BEGIN
                                    DECLARE v_max INT DEFAULT 0;
    DECLARE v_next INT;

                                    SELECT COALESCE(MAX(CAST(SUBSTRING(userID,2) AS UNSIGNED)),0)
                                    INTO v_max
                                    FROM User
                                    WHERE userID REGEXP '^U[0-9]+$';

                                    SET v_next = v_max + 1;
    SET NEW.userID = CONCAT('U',LPAD(v_next,3,'0'));
END$$

                                    DELIMITER ;

-- =========================
-- ROLE
-- =========================
                                    CREATE TABLE Role (
                                                          roleID VARCHAR(10) PRIMARY KEY,
                                                          name VARCHAR(50) NOT NULL,
                                                          userID VARCHAR(10) NOT NULL,
                                                          FOREIGN KEY (userID) REFERENCES User(userID)
                                    );

                                    DELIMITER $$

                                    CREATE TRIGGER trg_before_insert_role
                                        BEFORE INSERT ON Role
                                        FOR EACH ROW
                                    BEGIN
                                        DECLARE v_max INT DEFAULT 0;
    DECLARE v_next INT;

                                        SELECT COALESCE(MAX(CAST(SUBSTRING(roleID,4) AS UNSIGNED)),0)
                                        INTO v_max
                                        FROM Role
                                        WHERE roleID REGEXP '^ROL[0-9]+$';

                                        SET v_next = v_max + 1;
    SET NEW.roleID = CONCAT('ROL',LPAD(v_next,3,'0'));
END$$

                                        DELIMITER ;