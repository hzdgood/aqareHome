CREATE TABLE `project`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `name`         varchar(45) DEFAULT NULL,
    `address`      varchar(45) DEFAULT NULL,
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
    `name`           varchar(45) DEFAULT NULL,
    `disabled`       varchar(45) DEFAULT NULL,
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
