CREATE DATABASE IF NOT EXISTS shop_management;
USE shop_management;

-- ============================================================
-- SCHEMA — All PKs are VARCHAR
-- ============================================================

-- ------------------------------------------------------------
-- Category   →  CAT001, CAT002 ...
-- ------------------------------------------------------------
CREATE TABLE Category (
                          categoryID  VARCHAR(10)  NOT NULL,
                          name        VARCHAR(100) NOT NULL,
                          description TEXT,
                          PRIMARY KEY (categoryID)
);

-- ------------------------------------------------------------
-- Supplier   →  SUP001, SUP002 ...
-- ------------------------------------------------------------
CREATE TABLE Supplier (
                          supplierID    VARCHAR(10)  NOT NULL,
                          name          VARCHAR(100) NOT NULL,
                          contactPerson VARCHAR(100),
                          phone         VARCHAR(20),
                          email         VARCHAR(100),
                          address       TEXT,
                          PRIMARY KEY (supplierID)
);

-- ------------------------------------------------------------
-- Customer   →  C001, C002 ...
-- ------------------------------------------------------------
CREATE TABLE Customer (
                          customerId  VARCHAR(10)  NOT NULL,
                          name        VARCHAR(100) NOT NULL,
                          phone       BIGINT,
                          email       VARCHAR(100),
                          loyaltyCode VARCHAR(50),
                          PRIMARY KEY (customerId)
);

-- ------------------------------------------------------------
-- User   →  U001, U002 ...
-- ------------------------------------------------------------
CREATE TABLE User (
                      userID    VARCHAR(10)  NOT NULL,
                      userName  VARCHAR(100) NOT NULL,
                      password  VARCHAR(255) NOT NULL,
                      active    BOOLEAN      NOT NULL DEFAULT TRUE,
                      createdAt DATE,
                      PRIMARY KEY (userID)
);

-- ------------------------------------------------------------
-- Role   →  ROL001, ROL002 ...
-- ------------------------------------------------------------
CREATE TABLE Role (
                      roleID VARCHAR(10)  NOT NULL,
                      name   VARCHAR(50)  NOT NULL,
                      userID VARCHAR(10)  NOT NULL,
                      PRIMARY KEY (roleID),
                      FOREIGN KEY (userID) REFERENCES User(userID)
);

-- ------------------------------------------------------------
-- Product   →  P001, P002 ...
-- ------------------------------------------------------------
CREATE TABLE Product (
                         productID  VARCHAR(10)    NOT NULL,
                         SKU        VARCHAR(100)   NOT NULL UNIQUE,
                         barCode    BIGINT,
                         name       VARCHAR(150)   NOT NULL,
                         unitPrice  DECIMAL(10, 2) NOT NULL,
                         qyt        INT            NOT NULL DEFAULT 0,
                         active     BOOLEAN        NOT NULL DEFAULT TRUE,
                         categoryID VARCHAR(10),
                         PRIMARY KEY (productID),
                         FOREIGN KEY (categoryID) REFERENCES Category(categoryID)
);

-- ------------------------------------------------------------
-- Orders   →  ORD001, ORD002 ...
-- ------------------------------------------------------------
CREATE TABLE orders (
                        id          VARCHAR(10)  NOT NULL,
                        date        DATE         NOT NULL,
                        customer_id VARCHAR(10),
                        PRIMARY KEY (id),
                        FOREIGN KEY (customer_id) REFERENCES Customer(customerId)
);

-- ------------------------------------------------------------
-- Order Products (line items)   →  OPR001, OPR002 ...
-- ------------------------------------------------------------
CREATE TABLE order_products (
                                id         VARCHAR(10)    NOT NULL,
                                order_id   VARCHAR(10)    NOT NULL,
                                product_id VARCHAR(10)    NOT NULL,
                                qty        INT            NOT NULL,
                                price      DECIMAL(10, 2) NOT NULL,
                                PRIMARY KEY (id),
                                FOREIGN KEY (order_id)   REFERENCES orders(id),
                                FOREIGN KEY (product_id) REFERENCES Product(productID)
);

-- ------------------------------------------------------------
-- Payment   →  PAY001, PAY002 ...
-- ------------------------------------------------------------
CREATE TABLE Payment (
                         payment_id  VARCHAR(10)    NOT NULL,
                         customer_id VARCHAR(10),
                         method      VARCHAR(50),
                         amount      DECIMAL(10, 2) NOT NULL,
                         reference   VARCHAR(100),
                         received_at DATE           NOT NULL,
                         PRIMARY KEY (payment_id),
                         FOREIGN KEY (customer_id) REFERENCES Customer(customerId)
);

-- ------------------------------------------------------------
-- Returns   →  RET001, RET002 ...
-- ------------------------------------------------------------
CREATE TABLE Returns (
                         returnId     VARCHAR(10)    NOT NULL,
                         paymentId    VARCHAR(10),
                         action       VARCHAR(50),
                         reason       TEXT,
                         refundAmount DECIMAL(10, 2),
                         returnDate   DATE           NOT NULL,
                         status       VARCHAR(50),
                         PRIMARY KEY (returnId),
                         FOREIGN KEY (paymentId) REFERENCES Payment(payment_id)
);

-- ------------------------------------------------------------
-- Expense   →  EXP001, EXP002 ...
-- ------------------------------------------------------------
CREATE TABLE expense (
                         id             VARCHAR(10)    NOT NULL,
                         title          VARCHAR(150)   NOT NULL,
                         category_type  VARCHAR(100),
                         amount         DECIMAL(10, 2) NOT NULL,
                         payment_method VARCHAR(50),
                         date           DATE           NOT NULL,
                         PRIMARY KEY (id)
);

-- ------------------------------------------------------------
-- Purchase Order   →  PO001, PO002 ...
-- ------------------------------------------------------------
CREATE TABLE purchase_order (
                                po_id         VARCHAR(10)    NOT NULL,
                                supplier_id   VARCHAR(10),
                                total_cost    DECIMAL(10, 2) NOT NULL,
                                status        VARCHAR(50),
                                create_at     DATE           NOT NULL,
                                expected_date DATE,
                                PRIMARY KEY (po_id),
                                FOREIGN KEY (supplier_id) REFERENCES Supplier(supplierID)
);


-- ============================================================
-- TRIGGERS — BEFORE INSERT, string-concat auto-increment IDs
-- ============================================================

-- ------------------------------------------------------------
-- Category  →  CAT001, CAT002 ...
-- ------------------------------------------------------------
DROP TRIGGER IF EXISTS trg_before_insert_category;
DELIMITER $$
CREATE TRIGGER trg_before_insert_category
    BEFORE INSERT ON Category
    FOR EACH ROW
BEGIN
    DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
    SELECT COALESCE(MAX(CAST(SUBSTRING(categoryID, 4) AS UNSIGNED)), 0)
    INTO v_max FROM Category WHERE categoryID REGEXP '^CAT[0-9]+$';
    SET v_next = v_max + 1;
    SET NEW.categoryID = CONCAT('CAT', LPAD(v_next, 3, '0'));
END$$
    DELIMITER ;

-- ------------------------------------------------------------
-- Supplier  →  SUP001, SUP002 ...
-- ------------------------------------------------------------
    DROP TRIGGER IF EXISTS trg_before_insert_supplier;
    DELIMITER $$
    CREATE TRIGGER trg_before_insert_supplier
        BEFORE INSERT ON Supplier
        FOR EACH ROW
    BEGIN
        DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
        SELECT COALESCE(MAX(CAST(SUBSTRING(supplierID, 4) AS UNSIGNED)), 0)
        INTO v_max FROM Supplier WHERE supplierID REGEXP '^SUP[0-9]+$';
        SET v_next = v_max + 1;
    SET NEW.supplierID = CONCAT('SUP', LPAD(v_next, 3, '0'));
END$$
        DELIMITER ;

-- ------------------------------------------------------------
-- Customer  →  C001, C002 ...
-- ------------------------------------------------------------
        DROP TRIGGER IF EXISTS trg_before_insert_customer;
        DELIMITER $$
        CREATE TRIGGER trg_before_insert_customer
            BEFORE INSERT ON Customer
            FOR EACH ROW
        BEGIN
            DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
            SELECT COALESCE(MAX(CAST(SUBSTRING(customerId, 2) AS UNSIGNED)), 0)
            INTO v_max FROM Customer WHERE customerId REGEXP '^C[0-9]+$';
            SET v_next = v_max + 1;
    SET NEW.customerId = CONCAT('C', LPAD(v_next, 3, '0'));
END$$
            DELIMITER ;

-- ------------------------------------------------------------
-- User  →  U001, U002 ...
-- ------------------------------------------------------------
            DROP TRIGGER IF EXISTS trg_before_insert_user;
            DELIMITER $$
            CREATE TRIGGER trg_before_insert_user
                BEFORE INSERT ON User
                FOR EACH ROW
            BEGIN
                DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
                SELECT COALESCE(MAX(CAST(SUBSTRING(userID, 2) AS UNSIGNED)), 0)
                INTO v_max FROM User WHERE userID REGEXP '^U[0-9]+$';
                SET v_next = v_max + 1;
    SET NEW.userID = CONCAT('U', LPAD(v_next, 3, '0'));
END$$
                DELIMITER ;

-- ------------------------------------------------------------
-- Role  →  ROL001, ROL002 ...
-- ------------------------------------------------------------
                DROP TRIGGER IF EXISTS trg_before_insert_role;
                DELIMITER $$
                CREATE TRIGGER trg_before_insert_role
                    BEFORE INSERT ON Role
                    FOR EACH ROW
                BEGIN
                    DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
                    SELECT COALESCE(MAX(CAST(SUBSTRING(roleID, 4) AS UNSIGNED)), 0)
                    INTO v_max FROM Role WHERE roleID REGEXP '^ROL[0-9]+$';
                    SET v_next = v_max + 1;
    SET NEW.roleID = CONCAT('ROL', LPAD(v_next, 3, '0'));
END$$
                    DELIMITER ;

-- ------------------------------------------------------------
-- Product  →  P001, P002 ...
-- ------------------------------------------------------------
                    DROP TRIGGER IF EXISTS trg_before_insert_product;
                    DELIMITER $$
                    CREATE TRIGGER trg_before_insert_product
                        BEFORE INSERT ON Product
                        FOR EACH ROW
                    BEGIN
                        DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
                        SELECT COALESCE(MAX(CAST(SUBSTRING(productID, 2) AS UNSIGNED)), 0)
                        INTO v_max FROM Product WHERE productID REGEXP '^P[0-9]+$';
                        SET v_next = v_max + 1;
    SET NEW.productID = CONCAT('P', LPAD(v_next, 3, '0'));
END$$
                        DELIMITER ;

-- ------------------------------------------------------------
-- orders  →  ORD001, ORD002 ...
-- ------------------------------------------------------------
                        DROP TRIGGER IF EXISTS trg_before_insert_orders;
                        DELIMITER $$
                        CREATE TRIGGER trg_before_insert_orders
                            BEFORE INSERT ON orders
                            FOR EACH ROW
                        BEGIN
                            DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
                            SELECT COALESCE(MAX(CAST(SUBSTRING(id, 4) AS UNSIGNED)), 0)
                            INTO v_max FROM orders WHERE id REGEXP '^ORD[0-9]+$';
                            SET v_next = v_max + 1;
    SET NEW.id = CONCAT('ORD', LPAD(v_next, 3, '0'));
END$$
                            DELIMITER ;

-- ------------------------------------------------------------
-- order_products  →  OPR001, OPR002 ...
-- ------------------------------------------------------------
                            DROP TRIGGER IF EXISTS trg_before_insert_order_products;
                            DELIMITER $$
                            CREATE TRIGGER trg_before_insert_order_products
                                BEFORE INSERT ON order_products
                                FOR EACH ROW
                            BEGIN
                                DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
                                SELECT COALESCE(MAX(CAST(SUBSTRING(id, 4) AS UNSIGNED)), 0)
                                INTO v_max FROM order_products WHERE id REGEXP '^OPR[0-9]+$';
                                SET v_next = v_max + 1;
    SET NEW.id = CONCAT('OPR', LPAD(v_next, 3, '0'));
END$$
                                DELIMITER ;

-- ------------------------------------------------------------
-- Payment  →  PAY001, PAY002 ...
-- ------------------------------------------------------------
                                DROP TRIGGER IF EXISTS trg_before_insert_payment;
                                DELIMITER $$
                                CREATE TRIGGER trg_before_insert_payment
                                    BEFORE INSERT ON Payment
                                    FOR EACH ROW
                                BEGIN
                                    DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
                                    SELECT COALESCE(MAX(CAST(SUBSTRING(payment_id, 4) AS UNSIGNED)), 0)
                                    INTO v_max FROM Payment WHERE payment_id REGEXP '^PAY[0-9]+$';
                                    SET v_next = v_max + 1;
    SET NEW.payment_id = CONCAT('PAY', LPAD(v_next, 3, '0'));
END$$
                                    DELIMITER ;

-- ------------------------------------------------------------
-- Returns  →  RET001, RET002 ...
-- ------------------------------------------------------------
                                    DROP TRIGGER IF EXISTS trg_before_insert_returns;
                                    DELIMITER $$
                                    CREATE TRIGGER trg_before_insert_returns
                                        BEFORE INSERT ON Returns
                                        FOR EACH ROW
                                    BEGIN
                                        DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
                                        SELECT COALESCE(MAX(CAST(SUBSTRING(returnId, 4) AS UNSIGNED)), 0)
                                        INTO v_max FROM Returns WHERE returnId REGEXP '^RET[0-9]+$';
                                        SET v_next = v_max + 1;
    SET NEW.returnId = CONCAT('RET', LPAD(v_next, 3, '0'));
END$$
                                        DELIMITER ;

-- ------------------------------------------------------------
-- expense  →  EXP001, EXP002 ...
-- ------------------------------------------------------------
                                        DROP TRIGGER IF EXISTS trg_before_insert_expense;
                                        DELIMITER $$
                                        CREATE TRIGGER trg_before_insert_expense
                                            BEFORE INSERT ON expense
                                            FOR EACH ROW
                                        BEGIN
                                            DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
                                            SELECT COALESCE(MAX(CAST(SUBSTRING(id, 4) AS UNSIGNED)), 0)
                                            INTO v_max FROM expense WHERE id REGEXP '^EXP[0-9]+$';
                                            SET v_next = v_max + 1;
    SET NEW.id = CONCAT('EXP', LPAD(v_next, 3, '0'));
END$$
                                            DELIMITER ;

-- ------------------------------------------------------------
-- purchase_order  →  PO001, PO002 ...
-- ------------------------------------------------------------
                                            DROP TRIGGER IF EXISTS trg_before_insert_purchase_order;
                                            DELIMITER $$
                                            CREATE TRIGGER trg_before_insert_purchase_order
                                                BEFORE INSERT ON purchase_order
                                                FOR EACH ROW
                                            BEGIN
                                                DECLARE v_max  INT DEFAULT 0;
    DECLARE v_next INT;
                                                SELECT COALESCE(MAX(CAST(SUBSTRING(po_id, 3) AS UNSIGNED)), 0)
                                                INTO v_max FROM purchase_order WHERE po_id REGEXP '^PO[0-9]+$';
                                                SET v_next = v_max + 1;
    SET NEW.po_id = CONCAT('PO', LPAD(v_next, 3, '0'));
END$$
                                                DELIMITER ;


-- ============================================================
-- SAMPLE DATA — IDs omitted so triggers fire automatically
-- TechMart Lanka (Pvt) Ltd — Colombo 03, Sri Lanka
-- ============================================================

-- ------------------------------------------------------------
-- Category  (triggers generate: CAT001 – CAT012)
-- ------------------------------------------------------------
                                                INSERT INTO Category (name, description) VALUES
                                                                                             ('Laptops',             'Portable computers for personal and professional use'),
                                                                                             ('Desktops',            'Desktop tower PCs and all-in-one systems'),
                                                                                             ('Monitors',            'LED, IPS and curved display monitors'),
                                                                                             ('Processors',          'Intel and AMD CPUs'),
                                                                                             ('RAM & Storage',       'Memory modules, SSDs and HDDs'),
                                                                                             ('Graphics Cards',      'Dedicated GPUs for gaming and design'),
                                                                                             ('Motherboards',        'ATX and micro-ATX motherboards'),
                                                                                             ('Networking',          'Routers, switches, LAN cards and accessories'),
                                                                                             ('Peripherals',         'Keyboards, mice, headsets and webcams'),
                                                                                             ('Power & UPS',         'Power supplies, UPS units and surge protectors'),
                                                                                             ('Printers & Scanners', 'Inkjet, laser printers and document scanners'),
                                                                                             ('Accessories',         'Cables, bags, cooling pads and other accessories');

                                                -- ------------------------------------------------------------
-- Supplier  (triggers generate: SUP001 – SUP008)
-- ------------------------------------------------------------
                                                INSERT INTO Supplier (name, contactPerson, phone, email, address) VALUES
                                                                                                                      ('Softlogic Holdings PLC',       'Nuwan Perera',        '0112345678', 'nuwan@softlogic.lk',        '14, De Fonseka Place, Colombo 05'),
                                                                                                                      ('Singer Sri Lanka PLC',         'Dilani Jayawardena',  '0112456789', 'dilani@singersl.lk',        '21, Galle Road, Colombo 03'),
                                                                                                                      ('Abans PLC',                    'Kamal Silva',         '0112567890', 'kamal@abans.lk',            '498, Galle Road, Colombo 03'),
                                                                                                                      ('Redline Solutions (Pvt) Ltd',  'Pradeep Fernando',    '0112678901', 'pradeep@redline.lk',        '32, Dharmapala Mawatha, Colombo 07'),
                                                                                                                      ('MetaData Systems (Pvt) Ltd',   'Samantha Gunasekara', '0112789012', 'sam@metadata.lk',           '78, Nawam Mawatha, Colombo 02'),
                                                                                                                      ('ICT Hub Lanka',                'Thilini Rathnayake',  '0112890123', 'thilini@icthub.lk',         '112, Bauddaloka Mawatha, Colombo 08'),
                                                                                                                      ('Daraz Business Supplies',      'Roshan Bandara',      '0771234567', 'roshan@daraz.lk',           'LevelUp, 8th Floor, Emaar Square, Colombo 02'),
                                                                                                                      ('Micro Solutions Lanka',        'Anjali De Silva',     '0772345678', 'anjali@microsolutions.lk',  '45, Hospital Road, Kandy 20000');

                                                -- ------------------------------------------------------------
-- Customer  (triggers generate: C001 – C015)
-- ------------------------------------------------------------
                                                INSERT INTO Customer (name, phone, email, loyaltyCode) VALUES
                                                                                                           ('Chaminda Wijesinghe',   711234567, 'chaminda.w@gmail.com',    'LYL-10021'),
                                                                                                           ('Nadeeka Perera',        722345678, 'nadeeka.p@yahoo.com',     'LYL-10022'),
                                                                                                           ('Ruchira Dissanayake',   733456789, 'ruchira.d@hotmail.com',   'LYL-10023'),
                                                                                                           ('Tharanga Jayasuriya',   744567890, 'tharanga.j@gmail.com',    'LYL-10024'),
                                                                                                           ('Sachini Kumari',        755678901, 'sachini.k@gmail.com',     'LYL-10025'),
                                                                                                           ('Malith Gunawardena',    766789012, 'malith.g@outlook.com',    'LYL-10026'),
                                                                                                           ('Oshadi Fernando',       777890123, 'oshadi.f@gmail.com',      'LYL-10027'),
                                                                                                           ('Kasun Rajapaksa',       788901234, 'kasun.r@gmail.com',       'LYL-10028'),
                                                                                                           ('Dimuthu Bandara',       799012345, 'dimuthu.b@gmail.com',     'LYL-10029'),
                                                                                                           ('Hiruni Wickramasinghe', 711023456, 'hiruni.w@yahoo.com',      'LYL-10030'),
                                                                                                           ('Lasith Madushanka',     722034567, 'lasith.m@gmail.com',      NULL),
                                                                                                           ('Amaya Senanayake',      733045678, 'amaya.s@gmail.com',       NULL),
                                                                                                           ('Buddhika Samaraweera',  744056789, 'buddhika.s@outlook.com',  'LYL-10033'),
                                                                                                           ('Dilshan Pathirana',     755067890, 'dilshan.p@gmail.com',     NULL),
                                                                                                           ('Nadeesha Rathnayake',   766078901, 'nadeesha.r@gmail.com',    'LYL-10035');

                                                -- ------------------------------------------------------------
-- User  (triggers generate: U001 – U006)
-- ------------------------------------------------------------
                                                INSERT INTO User (userName, password, active, createdAt) VALUES
                                                                                                             ('admin',         '$2a$10$adminHashPlaceholder001',  TRUE,  '2023-01-15'),
                                                                                                             ('kasun_cashier', '$2a$10$cashierHashPlaceholder02', TRUE,  '2023-02-01'),
                                                                                                             ('nimesha_mgr',   '$2a$10$managerHashPlaceholder03', TRUE,  '2023-02-01'),
                                                                                                             ('saman_stock',   '$2a$10$stockHashPlaceholder0004', TRUE,  '2023-03-10'),
                                                                                                             ('priya_sales',   '$2a$10$salesHashPlaceholder0005', TRUE,  '2023-04-05'),
                                                                                                             ('old_user',      '$2a$10$oldUserHashPlaceholder06', FALSE, '2022-11-20');

                                                -- ------------------------------------------------------------
-- Role  (triggers generate: ROL001 – ROL006)
-- ------------------------------------------------------------
                                                INSERT INTO Role (name, userID) VALUES
                                                                                    ('Administrator', 'U001'),
                                                                                    ('Cashier',       'U002'),
                                                                                    ('Manager',       'U003'),
                                                                                    ('Stock Keeper',  'U004'),
                                                                                    ('Sales Rep',     'U005'),
                                                                                    ('Cashier',       'U006');

                                                -- ------------------------------------------------------------
-- Product  (triggers generate: P001 – P034)
-- ------------------------------------------------------------
                                                INSERT INTO Product (SKU, barCode, name, unitPrice, qyt, active, categoryID) VALUES
-- Laptops (CAT001)
('LAP-ACER-A315',    1001001, 'Acer Aspire 3 A315 (Intel i5 / 8GB / 512GB SSD)',   149900.00, 12, TRUE, 'CAT001'),
('LAP-DELL-INS15',   1001002, 'Dell Inspiron 15 3520 (Intel i7 / 16GB / 512GB)',   249900.00,  8, TRUE, 'CAT001'),
('LAP-HP-245G9',     1001003, 'HP 245 G9 (Ryzen 5 / 8GB / 256GB SSD)',             129900.00, 15, TRUE, 'CAT001'),
('LAP-LENOVO-L340',  1001004, 'Lenovo IdeaPad L340 Gaming (i5 / GTX1650)',         239900.00,  5, TRUE, 'CAT001'),
('LAP-ASUS-X515',    1001005, 'ASUS VivoBook X515 (Intel i3 / 4GB / 256GB)',        99900.00, 20, TRUE, 'CAT001'),
-- Desktops (CAT002)
('DSK-ASUS-D500',    1002001, 'ASUS ExpertCenter D500 Tower (i5 / 8GB / 1TB)',     195000.00,  6, TRUE, 'CAT002'),
('DSK-HP-290G4',     1002002, 'HP ProDesk 290 G4 MT (i3 / 4GB / 500GB)',           145000.00,  9, TRUE, 'CAT002'),
-- Monitors (CAT003)
('MON-LG-22MK',      1003001, 'LG 22MK400H 22" Full HD LED Monitor',               35900.00, 18, TRUE, 'CAT003'),
('MON-DELL-P2422',   1003002, 'Dell P2422H 24" IPS Monitor',                        62500.00, 10, TRUE, 'CAT003'),
('MON-SAMSUNG-27',   1003003, 'Samsung 27" Curved Gaming Monitor (144Hz)',           89900.00,  7, TRUE, 'CAT003'),
-- Processors (CAT004)
('CPU-INTEL-I512',   1004001, 'Intel Core i5-12400 12th Gen Processor',             62000.00, 14, TRUE, 'CAT004'),
('CPU-AMD-R55600',   1004002, 'AMD Ryzen 5 5600X Processor',                        58000.00, 11, TRUE, 'CAT004'),
('CPU-INTEL-I712',   1004003, 'Intel Core i7-12700K Processor',                     98000.00,  6, TRUE, 'CAT004'),
-- RAM & Storage (CAT005)
('RAM-KING-8GB',     1005001, 'Kingston 8GB DDR4 3200MHz RAM',                       9500.00, 35, TRUE, 'CAT005'),
('RAM-CORS-16GB',    1005002, 'Corsair Vengeance 16GB DDR4 3600MHz',                18500.00, 22, TRUE, 'CAT005'),
('SSD-SAM-970EVO',   1005003, 'Samsung 970 EVO Plus 500GB NVMe SSD',                22000.00, 20, TRUE, 'CAT005'),
('HDD-SEA-1TB',      1005004, 'Seagate Barracuda 1TB 3.5" HDD',                      9800.00, 30, TRUE, 'CAT005'),
-- Graphics Cards (CAT006)
('GPU-NVID-RTX3060', 1006001, 'NVIDIA GeForce RTX 3060 12GB GDDR6',               145000.00,  5, TRUE, 'CAT006'),
('GPU-AMD-RX6600',   1006002, 'AMD Radeon RX 6600 8GB GDDR6',                     115000.00,  4, TRUE, 'CAT006'),
-- Motherboards (CAT007)
('MB-ASUS-B660M',    1007001, 'ASUS PRIME B660M-A DDR4 mATX Motherboard',          39500.00,  8, TRUE, 'CAT007'),
('MB-MSI-B550',      1007002, 'MSI MAG B550 TOMAHAWK ATX Motherboard',             52000.00,  6, TRUE, 'CAT007'),
-- Networking (CAT008)
('NET-TPLINK-AX20',  1008001, 'TP-Link Archer AX20 AX1800 Wi-Fi 6 Router',         18500.00, 12, TRUE, 'CAT008'),
('NET-DLINK-8P',     1008002, 'D-Link DGS-1008A 8-Port Gigabit Switch',              8900.00, 16, TRUE, 'CAT008'),
-- Peripherals (CAT009)
('PER-LOG-K230',     1009001, 'Logitech K230 Wireless Keyboard',                    4500.00, 40, TRUE, 'CAT009'),
('PER-LOG-M185',     1009002, 'Logitech M185 Wireless Mouse',                       3200.00, 50, TRUE, 'CAT009'),
('PER-COMBO-MKGO',   1009003, 'Logitech MK270r Wireless Keyboard & Mouse Combo',    7800.00, 25, TRUE, 'CAT009'),
('PER-HEAD-HV1000',  1009004, 'Havit H2002d Gaming Headset',                        5900.00, 20, TRUE, 'CAT009'),
-- Power & UPS (CAT010)
('UPS-APC-600',      1010001, 'APC Back-UPS 600VA UPS',                            19500.00, 10, TRUE, 'CAT010'),
('PSU-CORSAIR-650',  1010002, 'Corsair CV650 650W 80+ Bronze PSU',                 15900.00,  8, TRUE, 'CAT010'),
-- Printers (CAT011)
('PRN-HP-M15W',      1011001, 'HP LaserJet Pro M15w Wireless Printer',             38500.00,  7, TRUE, 'CAT011'),
('PRN-EPSON-L3250',  1011002, 'Epson EcoTank L3250 Wi-Fi Ink Tank Printer',        49900.00,  9, TRUE, 'CAT011'),
-- Accessories (CAT012)
('ACC-UGREEN-USBC',  1012001, 'Ugreen USB-C to HDMI Cable 2M',                      2900.00, 60, TRUE, 'CAT012'),
('ACC-BAG-SAMSON',   1012002, 'Samsonite 15.6" Laptop Backpack',                    8500.00, 18, TRUE, 'CAT012'),
('ACC-PAD-GAMING',   1012003, 'Havit MP830 XXL Gaming Mouse Pad',                   3500.00, 30, TRUE, 'CAT012');

                                                -- ------------------------------------------------------------
-- Orders  (triggers generate: ORD001 – ORD015)
-- ------------------------------------------------------------
                                                INSERT INTO orders (date, customer_id) VALUES
                                                                                           ('2024-11-05', 'C001'),
                                                                                           ('2024-11-08', 'C002'),
                                                                                           ('2024-11-12', 'C003'),
                                                                                           ('2024-11-15', 'C004'),
                                                                                           ('2024-11-20', 'C005'),
                                                                                           ('2024-11-22', 'C006'),
                                                                                           ('2024-12-01', 'C007'),
                                                                                           ('2024-12-05', 'C008'),
                                                                                           ('2024-12-10', 'C009'),
                                                                                           ('2024-12-14', 'C010'),
                                                                                           ('2024-12-18', 'C001'),
                                                                                           ('2025-01-03', 'C011'),
                                                                                           ('2025-01-07', 'C012'),
                                                                                           ('2025-01-10', 'C013'),
                                                                                           ('2025-01-15', 'C014');

                                                -- ------------------------------------------------------------
-- Order Products  (triggers generate: OPR001 – OPR040)
-- ------------------------------------------------------------
                                                INSERT INTO order_products (order_id, product_id, qty, price) VALUES
-- ORD001 : Laptop + peripherals
('ORD001', 'P001', 1, 149900.00),
('ORD001', 'P024', 1,   4500.00),
('ORD001', 'P025', 1,   3200.00),
-- ORD002 : Gaming build
('ORD002', 'P006', 1, 195000.00),
('ORD002', 'P018', 1, 145000.00),
('ORD002', 'P015', 2,  18500.00),
('ORD002', 'P010', 1,  89900.00),
-- ORD003 : Office setup
('ORD003', 'P007', 1, 145000.00),
('ORD003', 'P008', 2,  35900.00),
('ORD003', 'P026', 1,   7800.00),
('ORD003', 'P028', 1,  19500.00),
-- ORD004 : CPU upgrade
('ORD004', 'P011', 1,  62000.00),
('ORD004', 'P014', 2,   9500.00),
('ORD004', 'P016', 1,  22000.00),
-- ORD005 : Budget laptop
('ORD005', 'P005', 1,  99900.00),
('ORD005', 'P033', 1,   8500.00),
-- ORD006 : Network setup
('ORD006', 'P022', 2,  18500.00),
('ORD006', 'P023', 1,   8900.00),
('ORD006', 'P032', 2,   2900.00),
-- ORD007 : High-end laptop + monitor
('ORD007', 'P002', 1, 249900.00),
('ORD007', 'P009', 1,  62500.00),
-- ORD008 : Gaming peripherals
('ORD008', 'P027', 1,   5900.00),
('ORD008', 'P034', 1,   3500.00),
('ORD008', 'P025', 1,   3200.00),
-- ORD009 : Printer bundle
('ORD009', 'P031', 1,  49900.00),
('ORD009', 'P032', 1,   2900.00),
-- ORD010 : AMD build
('ORD010', 'P012', 1,  58000.00),
('ORD010', 'P021', 1,  52000.00),
('ORD010', 'P019', 1, 115000.00),
('ORD010', 'P017', 1,   9800.00),
-- ORD011 : UPS & PSU
('ORD011', 'P028', 1,  19500.00),
('ORD011', 'P029', 1,  15900.00),
-- ORD012 : HP laptop
('ORD012', 'P003', 1, 129900.00),
('ORD012', 'P033', 1,   8500.00),
-- ORD013 : Office printer
('ORD013', 'P030', 1,  38500.00),
-- ORD014 : RAM upgrade
('ORD014', 'P015', 1,  18500.00),
('ORD014', 'P016', 1,  22000.00),
-- ORD015 : Full peripherals set
('ORD015', 'P026', 1,   7800.00),
('ORD015', 'P027', 1,   5900.00),
('ORD015', 'P034', 1,   3500.00);

                                                -- ------------------------------------------------------------
-- Payment  (triggers generate: PAY001 – PAY015)
-- ------------------------------------------------------------
                                                INSERT INTO Payment (customer_id, method, amount, reference, received_at) VALUES
                                                                                                                              ('C001', 'Cash',           157600.00, 'CASH-001',        '2024-11-05'),
                                                                                                                              ('C002', 'Card',           485900.00, 'VISA-7821',       '2024-11-08'),
                                                                                                                              ('C003', 'Bank Transfer',  280500.00, 'BOC-TXN-443321',  '2024-11-12'),
                                                                                                                              ('C004', 'Card',           103500.00, 'MASTER-9934',     '2024-11-15'),
                                                                                                                              ('C005', 'Cash',           108400.00, 'CASH-005',        '2024-11-20'),
                                                                                                                              ('C006', 'HNB Online',      49800.00, 'HNB-TXN-887623',  '2024-11-22'),
                                                                                                                              ('C007', 'Card',           312400.00, 'VISA-4412',       '2024-12-01'),
                                                                                                                              ('C008', 'Cash',            12600.00, 'CASH-008',        '2024-12-05'),
                                                                                                                              ('C009', 'Card',            52800.00, 'MASTER-6678',     '2024-12-10'),
                                                                                                                              ('C010', 'Bank Transfer',  234800.00, 'COMM-TXN-221445', '2024-12-14'),
                                                                                                                              ('C001', 'Cash',            35400.00, 'CASH-011',        '2024-12-18'),
                                                                                                                              ('C011', 'Card',           138400.00, 'VISA-3301',       '2025-01-03'),
                                                                                                                              ('C012', 'Cash',            38500.00, 'CASH-013',        '2025-01-07'),
                                                                                                                              ('C013', 'Sampath Online',  40500.00, 'SAMP-TXN-993312', '2025-01-10'),
                                                                                                                              ('C014', 'Card',            17200.00, 'MASTER-2298',     '2025-01-15');

                                                -- ------------------------------------------------------------
-- Returns  (triggers generate: RET001 – RET005)
-- ------------------------------------------------------------
                                                INSERT INTO Returns (paymentId, action, reason, refundAmount, returnDate, status) VALUES
                                                                                                                                      ('PAY002', 'Refund',   'Monitor received with dead pixels',         35900.00, '2024-11-15', 'Approved'),
                                                                                                                                      ('PAY003', 'Exchange', 'Keyboard malfunctioned after 3 days',        7800.00, '2024-11-18', 'Approved'),
                                                                                                                                      ('PAY007', 'Refund',   'Customer changed mind about monitor model', 62500.00, '2024-12-08', 'Pending'),
                                                                                                                                      ('PAY009', 'Exchange', 'Printer paper feed issue out of box',       49900.00, '2024-12-13', 'Approved'),
                                                                                                                                      ('PAY012', 'Refund',   'Laptop screen flickering on arrival',      129900.00, '2025-01-06', 'Pending');

                                                -- ------------------------------------------------------------
-- Expense  (triggers generate: EXP001 – EXP015)
-- ------------------------------------------------------------
                                                INSERT INTO expense (title, category_type, amount, payment_method, date) VALUES
                                                                                                                             ('Shop Rent – November 2024',        'Rent',        185000.00, 'Bank Transfer', '2024-11-01'),
                                                                                                                             ('Electricity Bill – October 2024',  'Utilities',    22500.00, 'Online',        '2024-11-03'),
                                                                                                                             ('Staff Salaries – November 2024',   'Salaries',    350000.00, 'Bank Transfer', '2024-11-30'),
                                                                                                                             ('Internet & Phone – November',      'Utilities',     8500.00, 'Online',        '2024-11-05'),
                                                                                                                             ('Shop Cleaning & Maintenance',      'Maintenance',   5500.00, 'Cash',          '2024-11-10'),
                                                                                                                             ('Shop Rent – December 2024',        'Rent',        185000.00, 'Bank Transfer', '2024-12-01'),
                                                                                                                             ('Electricity Bill – November 2024', 'Utilities',    24800.00, 'Online',        '2024-12-03'),
                                                                                                                             ('Advertising – Facebook & Google',  'Marketing',    32000.00, 'Card',          '2024-12-05'),
                                                                                                                             ('Staff Salaries – December 2024',   'Salaries',    350000.00, 'Bank Transfer', '2024-12-31'),
                                                                                                                             ('Stationary & Packaging Supplies',  'Operations',    4200.00, 'Cash',          '2024-12-07'),
                                                                                                                             ('Shop Rent – January 2025',         'Rent',        185000.00, 'Bank Transfer', '2025-01-01'),
                                                                                                                             ('Security Service Fee',             'Operations',   18000.00, 'Bank Transfer', '2025-01-02'),
                                                                                                                             ('Electricity Bill – December 2024', 'Utilities',    26100.00, 'Online',        '2025-01-04'),
                                                                                                                             ('New Display Shelving',             'Equipment',    45000.00, 'Bank Transfer', '2025-01-08'),
                                                                                                                             ('Vehicle Fuel – Delivery',          'Logistics',     7800.00, 'Cash',          '2025-01-12');

                                                -- ------------------------------------------------------------
-- Purchase Order  (triggers generate: PO001 – PO010)
-- ------------------------------------------------------------
                                                INSERT INTO purchase_order (supplier_id, total_cost, status, create_at, expected_date) VALUES
                                                                                                                                           ('SUP001', 748000.00, 'Delivered',  '2024-10-15', '2024-10-22'),
                                                                                                                                           ('SUP003', 325000.00, 'Delivered',  '2024-10-20', '2024-10-28'),
                                                                                                                                           ('SUP005', 156000.00, 'Delivered',  '2024-11-01', '2024-11-08'),
                                                                                                                                           ('SUP002', 498000.00, 'Delivered',  '2024-11-10', '2024-11-18'),
                                                                                                                                           ('SUP004', 215000.00, 'Delivered',  '2024-11-20', '2024-11-27'),
                                                                                                                                           ('SUP006', 390000.00, 'Delivered',  '2024-12-02', '2024-12-10'),
                                                                                                                                           ('SUP007', 285000.00, 'In Transit', '2024-12-15', '2024-12-23'),
                                                                                                                                           ('SUP008', 112000.00, 'Pending',    '2024-12-20', '2025-01-05'),
                                                                                                                                           ('SUP001', 895000.00, 'Confirmed',  '2025-01-08', '2025-01-18'),
                                                                                                                                           ('SUP005', 432000.00, 'Pending',    '2025-01-10', '2025-01-20');

