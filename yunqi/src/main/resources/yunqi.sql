CREATE TABLE `project`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `itemId`       varchar(45) DEFAULT NULL,
    `code`         varchar(45) DEFAULT NULL,
    `addPayReq`    varchar(45) DEFAULT NULL,
    `task`         varchar(45) DEFAULT NULL,
    `progress`     varchar(45) DEFAULT NULL,
    `saleConfirm`  varchar(45) DEFAULT NULL,
    `end`          varchar(45) DEFAULT NULL,
    `customer`     varchar(45) DEFAULT NULL,
    `projectId`    varchar(45) DEFAULT NULL,
    `name`         varchar(45) DEFAULT NULL,
    `telephone`    varchar(45) DEFAULT NULL,
    `department`   varchar(45) DEFAULT NULL,
    `sales`        varchar(45) DEFAULT NULL,
    `techId`       varchar(45) DEFAULT NULL,
    `projectType`  varchar(45) DEFAULT NULL,
    `designer`     varchar(45) DEFAULT NULL,
    `intention`    varchar(45) DEFAULT NULL,
    `track`        varchar(45) DEFAULT NULL,
    `preferential` varchar(45) DEFAULT NULL,
    `totalPrice`   varchar(45) DEFAULT NULL,
    `fullPrice`    varchar(45) DEFAULT NULL,
    `totalAmount`  varchar(45) DEFAULT NULL,
    `mainProject`  varchar(45) DEFAULT NULL,
    `groupID`      varchar(45) DEFAULT NULL,
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
    `realName`       varchar(45) DEFAULT NULL,
    `code`           varchar(45) DEFAULT NULL,
    `bomType`        varchar(45) DEFAULT NULL,
    `classification` varchar(45) DEFAULT NULL,
    `scanCode`       varchar(45) DEFAULT NULL,
    `shipType`       varchar(45) DEFAULT NULL,
    `repairPrice`    varchar(45) DEFAULT NULL,
    `purchasePrice`  varchar(45) DEFAULT NULL,
    `price`          varchar(45) DEFAULT NULL,
    `brand`          varchar(45) DEFAULT NULL,
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
    `itemId`           varchar(45) DEFAULT NULL,
    `projectId`        varchar(45) DEFAULT NULL,
    `productId`        varchar(45) DEFAULT NULL,
    `contractQuantity` varchar(45) DEFAULT NULL,
    `orderNumber`      varchar(45) DEFAULT NULL,
    `customerName`     varchar(45) DEFAULT NULL,
    `telephone`        varchar(45) DEFAULT NULL,
    `productName`      varchar(45) DEFAULT NULL,
    `productCode`      varchar(45) DEFAULT NULL,
    `number`           varchar(45) DEFAULT NULL,
    `money`            varchar(45) DEFAULT NULL,
    `yesNoFee`         varchar(45) DEFAULT NULL,
    `serviceFee`       varchar(45) DEFAULT NULL,
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
    `itemId`       varchar(45) DEFAULT NULL,
    `custom`       varchar(45) DEFAULT NULL,
    `technology`   varchar(45) DEFAULT NULL,
    `actualWork`   varchar(45) DEFAULT NULL,
    `workType`     varchar(45) DEFAULT NULL,
    `workStatus`   varchar(45) DEFAULT NULL,
    `orderDate`    datetime    DEFAULT NULL,
    `dateOfVisit`  datetime    DEFAULT NULL,
    `completeTime` datetime    DEFAULT NULL,
    `signInTime`   datetime    DEFAULT NULL,
    `area`         varchar(45) DEFAULT NULL,
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
