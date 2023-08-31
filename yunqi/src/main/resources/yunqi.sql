CREATE TABLE `project`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `saleId`       int NOT NULL,
    `techID`       int NOT NULL,
    `name`         varchar(45) DEFAULT NULL,
    `telephone`    varchar(45) DEFAULT NULL,
    `address`      varchar(160) DEFAULT NULL,
    `type`         varchar(45) DEFAULT NULL,
    `node`         varchar(45) DEFAULT NULL,
    `status`       varchar(45) DEFAULT NULL,
    `houseState`   varchar(45) DEFAULT NULL,
    `situation`    varchar(45) DEFAULT NULL,
    `village`      varchar(45) DEFAULT NULL,
    `schedule`     varchar(45) DEFAULT NULL,
    `region`       varchar(45) DEFAULT NULL,
    `createName`   varchar(45) DEFAULT NULL,
    `createTime`   datetime    DEFAULT NULL,
    `updateName`   varchar(45) DEFAULT NULL,
    `updateTime`   datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 125
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `product`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `name`           varchar(100) DEFAULT NULL,
    `code`           varchar(45) DEFAULT NULL,
    `classification` varchar(45) DEFAULT NULL,
    `shipType`       varchar(45) DEFAULT NULL,
    `price`          varchar(45) DEFAULT NULL,
    `brand`          varchar(45) DEFAULT NULL,
    `category`       varchar(45) DEFAULT NULL,
    `createName`     varchar(45) DEFAULT NULL,
    `createTime`     datetime    DEFAULT NULL,
    `updateName`     varchar(45) DEFAULT NULL,
    `updateTime`     datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4430
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `scheme`
(
    `id`               int NOT NULL AUTO_INCREMENT,
    `projectId`        int NOT NULL,
    `productId`        int NOT NULL,
    `number`           int DEFAULT 0,
    `installNumber`    int DEFAULT 0,
    `debugNumber`      int DEFAULT 0,
    `notInstalled`     int DEFAULT 0,
    `unregulated`      int DEFAULT 0,
    `unissued`         int DEFAULT 0,
    `createName`       varchar(45) DEFAULT NULL,
    `createTime`       datetime    DEFAULT NULL,
    `updateName`       varchar(45) DEFAULT NULL,
    `updateTime`       datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 53822
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `worksheet`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `projectId`    int NOT NULL,
    `techIds`      varchar(45) NOT NULL,
    `dateOfVisit`  datetime    DEFAULT NULL,
    `orderDate`    datetime    DEFAULT NULL,
    `type`         varchar(45) DEFAULT NULL,
    `status`       varchar(45) DEFAULT NULL,
    `actualWork`   varchar(45) DEFAULT NULL,
    `schedule`     varchar(45) DEFAULT NULL,
    `watermark`    varchar(45) DEFAULT NULL,
    `signImg`      varchar(45) DEFAULT NULL,
    `signTime`    datetime    DEFAULT NULL,
    `writeTime`   datetime    DEFAULT NULL,
    `disabled`     varchar(45) DEFAULT NULL,
    `createName`   varchar(45) DEFAULT NULL,
    `createTime`   datetime    DEFAULT NULL,
    `updateName`   varchar(45) DEFAULT NULL,
    `updateTime`   datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 327
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `login`
(
    `id`               int NOT NULL AUTO_INCREMENT,
    `username`         varchar(45) DEFAULT NULL,
    `password`         varchar(45) DEFAULT NULL,
    `techId`           int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 53822
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `commission`
(
    `id`               int NOT NULL AUTO_INCREMENT,
    `category`         varchar(45) DEFAULT NULL,
    `cost`         varchar(45) DEFAULT NULL,
    `installRatio`         varchar(45) DEFAULT NULL,
    `debugRatio`         varchar(45) DEFAULT NULL,
    `install`         varchar(45) DEFAULT NULL,
    `debug`         varchar(45) DEFAULT NULL,
    `disclose`         varchar(45) DEFAULT NULL,
    `check`         varchar(45) DEFAULT NULL,
    `headDoor`         varchar(45) DEFAULT NULL,
    `headDisclose`         varchar(45) DEFAULT NULL,
    `headComplete`         varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 53822
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `sales`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `name`         varchar(45) DEFAULT NULL,
    `telephone`    varchar(45) DEFAULT NULL,
    `status`     varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 327
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `tech`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `name`         varchar(45) DEFAULT NULL,
    `telephone`    varchar(45) DEFAULT NULL,
    `status`       varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 327
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `huoban`
(
    `id`      int NOT NULL AUTO_INCREMENT,
    `ticket`  varchar(45) DEFAULT NULL,
    `date`    datetime    DEFAULT NULL,
    `expired` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE `log`
(
    `id`     int NOT NULL AUTO_INCREMENT,
    `name`   varchar(45)  DEFAULT NULL,
    `custom` varchar(45)  DEFAULT NULL,
    `date`   datetime     DEFAULT NULL,
    `info`   varchar(200) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
