INSERT INTO branch (id, name, type, email, created_at, updated_at, status)
    VALUES(1, 'Head Office', 'HEAD_OFFICE', 'msadeeshaeranga@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');
INSERT INTO branch (id, name, type, email, created_at, updated_at, status)
    VALUES(2, 'Normal Branch 1', 'NORMAL', 'sadeeshae@ceyentra.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');

INSERT INTO branch_user(id, name, username, password, created_at, updated_at, branch_id)
    VALUES(1, 'Super Admin', 'super_admin', '$2y$12$vZUL2x2eTS9woA4Ozcs5veLoUXFQ4Xb1/iSY66aoDSStoyd7i6iYa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
INSERT INTO branch_user(id, name, username, password, created_at, updated_at, branch_id)
    VALUES(2, 'Branch Admin', 'branch_admin', '$2y$12$vZUL2x2eTS9woA4Ozcs5veLoUXFQ4Xb1/iSY66aoDSStoyd7i6iYa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO customer(id, name, nic, email)
    VALUES (1, 'Mary Jane', '123456789V', 'msadeeshaeranga@gmail.com');

INSERT INTO driver(id, name, nic, mobile)
    VALUES (1, 'John Doe', '123456789V', '0778536360');

INSERT INTO vehicle(id, vehicle_no, driver_id)
VALUES (1, 'ABC-1245', 1);

INSERT INTO product(id, name, unit)
    VALUES (1, 'Rice', 'Kg');
INSERT INTO product(id, name, unit)
    VALUES (2, 'Flour', 'g');

INSERT INTO stock(id, total_qty, remaining_qty, unit_price, branch_id, product_id)
    VALUES (1, 100, 100, 75.00, 1, 1);
INSERT INTO stock(id, total_qty, remaining_qty, unit_price, branch_id, product_id)
    VALUES (2, 100, 100, 100.00, 1, 2);
INSERT INTO stock(id, total_qty, remaining_qty, unit_price, branch_id, product_id)
    VALUES (3, 100, 100, 75.00, 2, 1);
INSERT INTO stock(id, total_qty, remaining_qty, unit_price, branch_id, product_id)
    VALUES (4, 100, 100, 100.00, 2, 2);



-------------------------------------------------------------------------------------------

-- DROP DATABASE IF EXISTS jk_sales;
--
-- CREATE DATABASE jk_sales;
--
-- USE jk_sales;
--
-- DROP TABLE IF EXISTS `branch`;
-- CREATE TABLE `branch` (
--                           `id` int NOT NULL AUTO_INCREMENT,
--                           `created_at` datetime(6) DEFAULT NULL,
--                           `email` varchar(255) NOT NULL,
--                           `name` varchar(255) NOT NULL,
--                           `status` varchar(255) DEFAULT NULL,
--                           `type` varchar(255) DEFAULT NULL,
--                           `updated_at` datetime(6) DEFAULT NULL,
--                           PRIMARY KEY (`id`),
--                           UNIQUE KEY `UK_q5alqrisfvpjk2t07gw4sofe` (`email`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- LOCK TABLES `branch` WRITE;
-- /*!40000 ALTER TABLE `branch` DISABLE KEYS */;
-- INSERT INTO `branch` VALUES (1,'2021-02-27 15:03:32.000000','msadeeshaeranga@gmail.com','Head Office','ACTIVE','HEAD_OFFICE','2021-02-27 15:03:32.000000'),(2,'2021-02-27 15:03:32.000000','sadeeshae@ceyentra.com','Normal Branch 1','ACTIVE','NORMAL','2021-02-27 15:03:32.000000');
-- /*!40000 ALTER TABLE `branch` ENABLE KEYS */;
-- UNLOCK TABLES;
--
-- DROP TABLE IF EXISTS `branch_user`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `branch_user` (
--                                `id` int NOT NULL AUTO_INCREMENT,
--                                `created_at` datetime(6) DEFAULT NULL,
--                                `name` varchar(255) DEFAULT NULL,
--                                `password` varchar(255) NOT NULL,
--                                `updated_at` datetime(6) DEFAULT NULL,
--                                `username` varchar(255) NOT NULL,
--                                `branch_id` int DEFAULT NULL,
--                                PRIMARY KEY (`id`),
--                                UNIQUE KEY `UK_r1nrtbc4ce2qrbl0mkd3v6nbw` (`username`),
--                                KEY `FKg973x7lagvfhn5vxdb2w9h8l7` (`branch_id`),
--                                CONSTRAINT `FKg973x7lagvfhn5vxdb2w9h8l7` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;
--
--
-- LOCK TABLES `branch_user` WRITE;
-- /*!40000 ALTER TABLE `branch_user` DISABLE KEYS */;
-- INSERT INTO `branch_user` VALUES (1,'2021-02-27 15:03:32.000000','Super Admin','$2y$12$vZUL2x2eTS9woA4Ozcs5veLoUXFQ4Xb1/iSY66aoDSStoyd7i6iYa','2021-02-27 15:03:32.000000','super_admin',1),(2,'2021-02-27 15:03:32.000000','Branch Admin','$2y$12$vZUL2x2eTS9woA4Ozcs5veLoUXFQ4Xb1/iSY66aoDSStoyd7i6iYa','2021-02-27 15:03:32.000000','branch_admin',1);
-- /*!40000 ALTER TABLE `branch_user` ENABLE KEYS */;
-- UNLOCK TABLES;
--
--
-- DROP TABLE IF EXISTS `customer`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `customer` (
--                             `id` bigint NOT NULL AUTO_INCREMENT,
--                             `created_at` datetime(6) DEFAULT NULL,
--                             `email` varchar(255) DEFAULT NULL,
--                             `name` varchar(255) NOT NULL,
--                             `nic` varchar(255) NOT NULL,
--                             `updated_at` datetime(6) DEFAULT NULL,
--                             PRIMARY KEY (`id`),
--                             UNIQUE KEY `UK_9st6x9trhf0s27g0vgpcaeu3m` (`nic`),
--                             UNIQUE KEY `UK_dwk6cx0afu8bs9o4t536v1j5v` (`email`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;
--
--
-- LOCK TABLES `customer` WRITE;
-- /*!40000 ALTER TABLE `customer` DISABLE KEYS */;
-- INSERT INTO `customer` VALUES (1,NULL,'msadeeshaeranga@gmail.com','Mary Jane','123456789V',NULL);
-- /*!40000 ALTER TABLE `customer` ENABLE KEYS */;
-- UNLOCK TABLES;
--
--
-- DROP TABLE IF EXISTS `customer_order`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `customer_order` (
--                                   `id` bigint NOT NULL AUTO_INCREMENT,
--                                   `created_at` datetime(6) DEFAULT NULL,
--                                   `payment_method` varchar(255) DEFAULT NULL,
--                                   `total` decimal(19,2) DEFAULT NULL,
--                                   `branch_id` int DEFAULT NULL,
--                                   `customer_id` bigint DEFAULT NULL,
--                                   PRIMARY KEY (`id`),
--                                   KEY `FKacvy3qv44w6at548n4qpy21te` (`branch_id`),
--                                   KEY `FKf9abd30bhiqvugayxlpq8ryq9` (`customer_id`),
--                                   CONSTRAINT `FKacvy3qv44w6at548n4qpy21te` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
--                                   CONSTRAINT `FKf9abd30bhiqvugayxlpq8ryq9` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;
--
-- --
-- -- Dumping data for table `customer_order`
-- --
--
-- LOCK TABLES `customer_order` WRITE;
-- /*!40000 ALTER TABLE `customer_order` DISABLE KEYS */;
-- /*!40000 ALTER TABLE `customer_order` ENABLE KEYS */;
-- UNLOCK TABLES;
--
-- --
-- -- Table structure for table `driver`
-- --
--
-- DROP TABLE IF EXISTS `driver`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `driver` (
--                           `id` int NOT NULL AUTO_INCREMENT,
--                           `created_at` datetime(6) DEFAULT NULL,
--                           `mobile` varchar(255) DEFAULT NULL,
--                           `name` varchar(255) DEFAULT NULL,
--                           `nic` varchar(255) NOT NULL,
--                           `updated_at` datetime(6) DEFAULT NULL,
--                           PRIMARY KEY (`id`),
--                           UNIQUE KEY `UK_3uwrkhrnjh7vooab613cxai8l` (`nic`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;
--
-- --
-- -- Dumping data for table `driver`
-- --
--
-- LOCK TABLES `driver` WRITE;
-- /*!40000 ALTER TABLE `driver` DISABLE KEYS */;
-- INSERT INTO `driver` VALUES (1,NULL,'0778536360','John Doe','123456789V',NULL);
-- /*!40000 ALTER TABLE `driver` ENABLE KEYS */;
-- UNLOCK TABLES;
--
-- --
-- -- Table structure for table `order_detail`
-- --
--
-- DROP TABLE IF EXISTS `order_detail`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `order_detail` (
--                                 `id` bigint NOT NULL AUTO_INCREMENT,
--                                 `amount` decimal(19,2) DEFAULT NULL,
--                                 `qty` int NOT NULL,
--                                 `customer_order_id` bigint DEFAULT NULL,
--                                 `stock_id` bigint DEFAULT NULL,
--                                 PRIMARY KEY (`id`),
--                                 KEY `FKdrhjbfo2ihc2wdkmaajtodsqh` (`customer_order_id`),
--                                 KEY `FKb49cefnejnqx3na7tuyom71oe` (`stock_id`),
--                                 CONSTRAINT `FKb49cefnejnqx3na7tuyom71oe` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`),
--                                 CONSTRAINT `FKdrhjbfo2ihc2wdkmaajtodsqh` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;
--
-- --
-- -- Dumping data for table `order_detail`
-- --
--
-- LOCK TABLES `order_detail` WRITE;
-- /*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
-- /*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
-- UNLOCK TABLES;
--
-- --
-- -- Table structure for table `product`
-- --
--
-- DROP TABLE IF EXISTS `product`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `product` (
--                            `id` int NOT NULL AUTO_INCREMENT,
--                            `created_at` datetime(6) DEFAULT NULL,
--                            `name` varchar(255) NOT NULL,
--                            `unit` varchar(255) DEFAULT NULL,
--                            `updated_at` datetime(6) DEFAULT NULL,
--                            PRIMARY KEY (`id`),
--                            UNIQUE KEY `UK_jmivyxk9rmgysrmsqw15lqr5b` (`name`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;
--
-- --
-- -- Dumping data for table `product`
-- --
--
-- LOCK TABLES `product` WRITE;
-- /*!40000 ALTER TABLE `product` DISABLE KEYS */;
-- INSERT INTO `product` VALUES (1,NULL,'Rice','Kg',NULL),(2,NULL,'Flour','g',NULL);
-- /*!40000 ALTER TABLE `product` ENABLE KEYS */;
-- UNLOCK TABLES;
--
-- --
-- -- Table structure for table `stock`
-- --
--
-- DROP TABLE IF EXISTS `stock`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `stock` (
--                          `id` bigint NOT NULL AUTO_INCREMENT,
--                          `created_at` datetime(6) DEFAULT NULL,
--                          `remaining_qty` int NOT NULL,
--                          `total_qty` int NOT NULL,
--                          `unit_price` decimal(19,2) DEFAULT NULL,
--                          `updated_at` datetime(6) DEFAULT NULL,
--                          `branch_id` int DEFAULT NULL,
--                          `product_id` int DEFAULT NULL,
--                          PRIMARY KEY (`id`),
--                          KEY `FK8wkpxnja0ikk6t0xp3ju8aoar` (`branch_id`),
--                          KEY `FKjghkvw2snnsr5gpct0of7xfcf` (`product_id`),
--                          CONSTRAINT `FK8wkpxnja0ikk6t0xp3ju8aoar` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
--                          CONSTRAINT `FKjghkvw2snnsr5gpct0of7xfcf` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;
--
-- --
-- -- Dumping data for table `stock`
-- --
--
-- LOCK TABLES `stock` WRITE;
-- /*!40000 ALTER TABLE `stock` DISABLE KEYS */;
-- INSERT INTO `stock` VALUES (1,NULL,100,100,75.00,NULL,1,1),(2,NULL,100,100,100.00,NULL,1,2),(3,NULL,100,100,75.00,NULL,2,1),(4,NULL,100,100,100.00,NULL,2,2);
-- /*!40000 ALTER TABLE `stock` ENABLE KEYS */;
-- UNLOCK TABLES;
--
-- --
-- -- Table structure for table `stock_request`
-- --
--
-- DROP TABLE IF EXISTS `stock_request`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `stock_request` (
--                                  `id` bigint NOT NULL AUTO_INCREMENT,
--                                  `created_at` datetime(6) DEFAULT NULL,
--                                  `status` varchar(255) DEFAULT NULL,
--                                  `updated_at` datetime(6) DEFAULT NULL,
--                                  `from_branch_id` int DEFAULT NULL,
--                                  `product_id` int DEFAULT NULL,
--                                  `to_branch_id` int DEFAULT NULL,
--                                  `vehicle_id` int DEFAULT NULL,
--                                  PRIMARY KEY (`id`),
--                                  KEY `FKipfdgggg67teyna5j2ghao59m` (`from_branch_id`),
--                                  KEY `FKc4yad3s73g26bhf1hl1cddxqi` (`product_id`),
--                                  KEY `FKob8nr58opjqep8vkx5xwd40nj` (`to_branch_id`),
--                                  KEY `FKo7yy42yob53p3d8qgwsvs0uf4` (`vehicle_id`),
--                                  CONSTRAINT `FKc4yad3s73g26bhf1hl1cddxqi` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
--                                  CONSTRAINT `FKipfdgggg67teyna5j2ghao59m` FOREIGN KEY (`from_branch_id`) REFERENCES `branch` (`id`),
--                                  CONSTRAINT `FKo7yy42yob53p3d8qgwsvs0uf4` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`),
--                                  CONSTRAINT `FKob8nr58opjqep8vkx5xwd40nj` FOREIGN KEY (`to_branch_id`) REFERENCES `branch` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;
--
-- --
-- -- Dumping data for table `stock_request`
-- --
--
-- LOCK TABLES `stock_request` WRITE;
-- /*!40000 ALTER TABLE `stock_request` DISABLE KEYS */;
-- /*!40000 ALTER TABLE `stock_request` ENABLE KEYS */;
-- UNLOCK TABLES;
--
-- --
-- -- Table structure for table `vehicle`
-- --
--
-- DROP TABLE IF EXISTS `vehicle`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `vehicle` (
--                            `id` int NOT NULL AUTO_INCREMENT,
--                            `created_at` datetime(6) DEFAULT NULL,
--                            `updated_at` datetime(6) DEFAULT NULL,
--                            `vehicle_no` varchar(255) NOT NULL,
--                            `driver_id` int DEFAULT NULL,
--                            PRIMARY KEY (`id`),
--                            UNIQUE KEY `UK_on0sjlojs25uuo4o6s2dwflt3` (`vehicle_no`),
--                            KEY `FKdpor9ohov2f3optwe7twe49tt` (`driver_id`),
--                            CONSTRAINT `FKdpor9ohov2f3optwe7twe49tt` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;
--
-- --
-- -- Dumping data for table `vehicle`
-- --
--
-- LOCK TABLES `vehicle` WRITE;
-- /*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
-- INSERT INTO `vehicle` VALUES (1,NULL,NULL,'ABC-1245',1);
-- /*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
-- UNLOCK TABLES;
