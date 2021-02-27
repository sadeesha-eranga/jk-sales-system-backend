DROP TABLE IF EXISTS `branch_user`;
DROP TABLE IF EXISTS `order_detail`;
DROP TABLE IF EXISTS `customer_order`;
DROP TABLE IF EXISTS `stock_request`;
DROP TABLE IF EXISTS `stock`;
DROP TABLE IF EXISTS `vehicle`;
DROP TABLE IF EXISTS `driver`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `customer`;
DROP TABLE IF EXISTS `branch`;

CREATE TABLE `branch` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `created_at` datetime(6) DEFAULT NULL,
                          `email` varchar(255) NOT NULL,
                          `name` varchar(255) NOT NULL,
                          `status` varchar(255) DEFAULT NULL,
                          `type` varchar(255) DEFAULT NULL,
                          `updated_at` datetime(6) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `UK_q5alqrisfvpjk2t07gw4sofe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `branch_user` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `created_at` datetime(6) DEFAULT NULL,
                               `name` varchar(255) DEFAULT NULL,
                               `password` varchar(255) NOT NULL,
                               `updated_at` datetime(6) DEFAULT NULL,
                               `username` varchar(255) NOT NULL,
                               `branch_id` int DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UK_r1nrtbc4ce2qrbl0mkd3v6nbw` (`username`),
                               KEY `FKg973x7lagvfhn5vxdb2w9h8l7` (`branch_id`),
                               CONSTRAINT `FKg973x7lagvfhn5vxdb2w9h8l7` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `created_at` datetime(6) DEFAULT NULL,
                           `name` varchar(255) NOT NULL,
                           `unit` varchar(255) DEFAULT NULL,
                           `updated_at` datetime(6) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UK_jmivyxk9rmgysrmsqw15lqr5b` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `stock` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `created_at` datetime(6) DEFAULT NULL,
                         `remaining_qty` int NOT NULL,
                         `total_qty` int NOT NULL,
                         `unit_price` decimal(19,2) DEFAULT NULL,
                         `updated_at` datetime(6) DEFAULT NULL,
                         `branch_id` int DEFAULT NULL,
                         `product_id` int DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FK8wkpxnja0ikk6t0xp3ju8aoar` (`branch_id`),
                         KEY `FKjghkvw2snnsr5gpct0of7xfcf` (`product_id`),
                         CONSTRAINT `FK8wkpxnja0ikk6t0xp3ju8aoar` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
                         CONSTRAINT `FKjghkvw2snnsr5gpct0of7xfcf` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `driver` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `created_at` datetime(6) DEFAULT NULL,
                          `mobile` varchar(255) DEFAULT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          `nic` varchar(255) NOT NULL,
                          `updated_at` datetime(6) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `UK_3uwrkhrnjh7vooab613cxai8l` (`nic`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vehicle` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `created_at` datetime(6) DEFAULT NULL,
                           `updated_at` datetime(6) DEFAULT NULL,
                           `vehicle_no` varchar(255) NOT NULL,
                           `driver_id` int DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UK_on0sjlojs25uuo4o6s2dwflt3` (`vehicle_no`),
                           KEY `FKdpor9ohov2f3optwe7twe49tt` (`driver_id`),
                           CONSTRAINT `FKdpor9ohov2f3optwe7twe49tt` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `stock_request` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `created_at` datetime(6) DEFAULT NULL,
                                 `status` varchar(255) DEFAULT NULL,
                                 `updated_at` datetime(6) DEFAULT NULL,
                                 `from_branch_id` int DEFAULT NULL,
                                 `product_id` int DEFAULT NULL,
                                 `to_branch_id` int DEFAULT NULL,
                                 `vehicle_id` int DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `FKipfdgggg67teyna5j2ghao59m` (`from_branch_id`),
                                 KEY `FKc4yad3s73g26bhf1hl1cddxqi` (`product_id`),
                                 KEY `FKob8nr58opjqep8vkx5xwd40nj` (`to_branch_id`),
                                 KEY `FKo7yy42yob53p3d8qgwsvs0uf4` (`vehicle_id`),
                                 CONSTRAINT `FKc4yad3s73g26bhf1hl1cddxqi` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
                                 CONSTRAINT `FKipfdgggg67teyna5j2ghao59m` FOREIGN KEY (`from_branch_id`) REFERENCES `branch` (`id`),
                                 CONSTRAINT `FKo7yy42yob53p3d8qgwsvs0uf4` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`),
                                 CONSTRAINT `FKob8nr58opjqep8vkx5xwd40nj` FOREIGN KEY (`to_branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `created_at` datetime(6) DEFAULT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `name` varchar(255) NOT NULL,
                            `nic` varchar(255) NOT NULL,
                            `updated_at` datetime(6) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `UK_9st6x9trhf0s27g0vgpcaeu3m` (`nic`),
                            UNIQUE KEY `UK_dwk6cx0afu8bs9o4t536v1j5v` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer_order` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `created_at` datetime(6) DEFAULT NULL,
                                  `payment_method` varchar(255) DEFAULT NULL,
                                  `total` decimal(19,2) DEFAULT NULL,
                                  `branch_id` int DEFAULT NULL,
                                  `customer_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKacvy3qv44w6at548n4qpy21te` (`branch_id`),
                                  KEY `FKf9abd30bhiqvugayxlpq8ryq9` (`customer_id`),
                                  CONSTRAINT `FKacvy3qv44w6at548n4qpy21te` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`),
                                  CONSTRAINT `FKf9abd30bhiqvugayxlpq8ryq9` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_detail` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `amount` decimal(19,2) DEFAULT NULL,
                                `qty` int NOT NULL,
                                `customer_order_id` bigint DEFAULT NULL,
                                `stock_id` bigint DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `FKdrhjbfo2ihc2wdkmaajtodsqh` (`customer_order_id`),
                                KEY `FKb49cefnejnqx3na7tuyom71oe` (`stock_id`),
                                CONSTRAINT `FKb49cefnejnqx3na7tuyom71oe` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`),
                                CONSTRAINT `FKdrhjbfo2ihc2wdkmaajtodsqh` FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;