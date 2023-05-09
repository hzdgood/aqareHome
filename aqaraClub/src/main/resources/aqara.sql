CREATE TABLE `survey`
(
    `id`                int NOT NULL AUTO_INCREMENT,
    `itemId`            varchar(45)  DEFAULT NULL,
    `code`              varchar(45)  DEFAULT NULL,
    `sales`             varchar(45)  DEFAULT NULL,
    `department`        varchar(45)  DEFAULT NULL,
    `appointmentTime`   datetime     DEFAULT NULL,
    `estimatedDuration` varchar(45)  DEFAULT NULL,
    `participants`      varchar(45)  DEFAULT NULL,
    `surveyPictures`    varchar(150) DEFAULT NULL,
    `surveyTask`        varchar(150) DEFAULT NULL,
    `createName`        varchar(45)  DEFAULT NULL,
    `createTime`        datetime     DEFAULT NULL,
    `updateName`        varchar(45)  DEFAULT NULL,
    `updateTime`        datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `quotation`
(
    `id`                 int NOT NULL AUTO_INCREMENT,
    `itemId`             varchar(45)  DEFAULT NULL,
    `code`               varchar(45) DEFAULT NULL,
    `sales`              varchar(45) DEFAULT NULL,
    `department`         varchar(45) DEFAULT NULL,
    `quotationType`      varchar(45) DEFAULT NULL,
    `quotationID`        varchar(45) DEFAULT NULL,
    `schemeAmount`       varchar(45) DEFAULT NULL,
    `preferentialAmount` varchar(45) DEFAULT NULL,
    `amountReceivable`   varchar(45) DEFAULT NULL,
    `amountReceived`     varchar(45) DEFAULT NULL,
    `uncollectedAmount`  varchar(45) DEFAULT NULL,
    `remarks`            varchar(45) DEFAULT NULL,
    `uploadContract`     varchar(45) DEFAULT NULL,
    `createName`         varchar(45) DEFAULT NULL,
    `createTime`         datetime    DEFAULT NULL,
    `updateName`         varchar(45) DEFAULT NULL,
    `updateTime`         datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `collent`
(
    `id`               int NOT NULL AUTO_INCREMENT,
    `itemId`           varchar(45)  DEFAULT NULL,
    `projectId`        varchar(45) DEFAULT NULL,
    `sales`            varchar(45) DEFAULT NULL,
    `department`       varchar(45) DEFAULT NULL,
    `projectStatus`    varchar(45) DEFAULT NULL,
    `collectionStatus` varchar(45) DEFAULT NULL,
    `refundType`       varchar(45) DEFAULT NULL,
    `paymentMethod`    varchar(45) DEFAULT NULL,
    `collection`       varchar(45) DEFAULT NULL,
    `quotationID`      varchar(45) DEFAULT NULL,
    `payee`            varchar(45) DEFAULT NULL,
    `crossCheck`       varchar(45) DEFAULT NULL,
    `createName`       varchar(45) DEFAULT NULL,
    `createTime`       datetime    DEFAULT NULL,
    `updateName`       varchar(45) DEFAULT NULL,
    `updateTime`       datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `customer`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `itemId`        varchar(45)  DEFAULT NULL,
    `name`          varchar(45)  DEFAULT NULL,
    `telephone`     varchar(45)  DEFAULT NULL,
    `sales`         varchar(45)  DEFAULT NULL,
    `sex`           varchar(45)  DEFAULT NULL,
    `source`        varchar(45)  DEFAULT NULL,
    `houseType`     varchar(45)  DEFAULT NULL,
    `houseState`    varchar(45)  DEFAULT NULL,
    `personnelAttr` varchar(45)  DEFAULT NULL,
    `customerAttr`  varchar(45)  DEFAULT NULL,
    `customerStage` varchar(45)  DEFAULT NULL,
    `SPcustomer`    varchar(45)  DEFAULT NULL,
    `houseDemand`   varchar(45)  DEFAULT NULL,
    `lossStatus`    varchar(45)  DEFAULT NULL,
    `weiXin`        varchar(45)  DEFAULT NULL,
    `UserID`        varchar(300) DEFAULT NULL,
    `releWeixin`    varchar(45)  DEFAULT NULL,
    `createName`    varchar(45)  DEFAULT NULL,
    `createTime`    datetime     DEFAULT NULL,
    `updateName`    varchar(45)  DEFAULT NULL,
    `updateTime`    datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3338 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `project`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `itemId`       varchar(45) DEFAULT NULL,
    `code`         varchar(45) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `product`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `itemId`         varchar(45) DEFAULT NULL,
    `name`           varchar(45) DEFAULT NULL,
    `realName`       varchar(45) DEFAULT NULL,
    `code`           varchar(45) DEFAULT NULL,--条码
    `scan`           varchar(45) DEFAULT NULL,--扫码
    `Measure`        varchar(45) DEFAULT NULL,--测量单
    `unit`           varchar(45) DEFAULT NULL,--单位
    `bomType`        varchar(45) DEFAULT NULL,--bom类型
    `performance`    varchar(45) DEFAULT NULL, --业绩
    `rebate`         varchar(45) DEFAULT NULL, --返利
    `dimmingRebate`  varchar(45) DEFAULT NULL, --调光返利
    `category`       varchar(45) DEFAULT NULL, --类别
    `sendType`       varchar(45) DEFAULT NULL, --发货类型
    `weight`         varchar(45) DEFAULT NULL, --重量
    `volume`         varchar(45) DEFAULT NULL, --体积
    `reserve`        varchar(45) DEFAULT NULL, --备用
    `disabled`       varchar(45) DEFAULT NULL, --禁用
    `repairPrice`    varchar(45) DEFAULT NULL, --维修
    `purchasePrice`  varchar(45) DEFAULT NULL, --进价
    `price`          varchar(45) DEFAULT NULL, --价格
    `brand`          varchar(45) DEFAULT NULL, --品牌
    `serviceFee`     varchar(45) DEFAULT NULL, --服务费
    `installFee`     varchar(45) DEFAULT NULL, --安装费
    `testFee`        varchar(45) DEFAULT NULL, --调试费
    `createName`     varchar(45) DEFAULT NULL,
    `createTime`     datetime    DEFAULT NULL,
    `updateName`     varchar(45) DEFAULT NULL,
    `updateTime`     datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1490 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


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
) ENGINE=InnoDB AUTO_INCREMENT=53822 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `worksheet`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `itemId`       varchar(45) DEFAULT NULL,
    `custom`       varchar(45) DEFAULT NULL,
    `technology`   varchar(45) DEFAULT NULL,
    `actualWork`   varchar(45) DEFAULT NULL,
    `workType`     varchar(45) DEFAULT NULL,
    `orderDate`    datetime    DEFAULT NULL,
    `dateOfVisit`  datetime    DEFAULT NULL,
    `completeTime` datetime    DEFAULT NULL,
    `area`         varchar(45) DEFAULT NULL,
    `createName`   varchar(45) DEFAULT NULL,
    `createTime`   datetime    DEFAULT NULL,
    `updateName`   varchar(45) DEFAULT NULL,
    `updateTime`   datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `code`       varchar(45) DEFAULT NULL,
    `userName`   varchar(32) DEFAULT NULL,
    `passWord`   varchar(50) DEFAULT NULL,
    `engName`    varchar(45) DEFAULT NULL,
    `sex`        varchar(45) DEFAULT NULL,
    `station`    varchar(45) DEFAULT NULL,
    `department` varchar(45) DEFAULT NULL,
    `status`     varchar(45) DEFAULT NULL,
    `company`    varchar(45) DEFAULT NULL,
    `level`      varchar(45) DEFAULT NULL,
    `restDay`    varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;


CREATE TABLE `weixin`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `name`       varchar(45) DEFAULT NULL,
    `type`       varchar(45) DEFAULT NULL,
    `sales`      varchar(45) DEFAULT NULL,
    `uuid`       varchar(45) DEFAULT NULL,
    `createName` varchar(45) DEFAULT NULL,
    `createTime` datetime    DEFAULT NULL,
    `updateName` varchar(45) DEFAULT NULL,
    `updateTime` datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `huoban`
(
    `id`      int NOT NULL AUTO_INCREMENT,
    `ticket`  varchar(45) DEFAULT NULL,
    `date`    datetime    DEFAULT NULL,
    `expired` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `wechat`
(
    `id`      int NOT NULL AUTO_INCREMENT,
    `ticket`  varchar(300) DEFAULT NULL,
    `type`    varchar(45)  DEFAULT NULL,
    `date`    datetime     DEFAULT NULL,
    `expired` varchar(45)  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `log`
(
    `id`     int NOT NULL AUTO_INCREMENT,
    `name`   varchar(45)  DEFAULT NULL,
    `custom` varchar(45)  DEFAULT NULL,
    `date`   datetime     DEFAULT NULL,
    `info`   varchar(200) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `speedycontent`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `themeId`      varchar(45)  DEFAULT NULL,
    `mediaId`      varchar(400) DEFAULT NULL,
    `contentTitle` varchar(45)  DEFAULT NULL,
    `contentText`  varchar(400) DEFAULT NULL,
    `contentImage` varchar(400) DEFAULT NULL,
    `contentVideo` varchar(400) DEFAULT NULL,
    `contentFile`  varchar(400) DEFAULT NULL,
    `contentType`  varchar(45)  DEFAULT NULL,
    `contentLevel` varchar(45)  DEFAULT NULL,
    `createName`   varchar(45)  DEFAULT NULL,
    `createTime`   datetime     DEFAULT NULL,
    `updateName`   varchar(45)  DEFAULT NULL,
    `updateTime`   datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=299 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `speedytheme`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `teamId`     varchar(45) DEFAULT NULL,
    `theme`      varchar(45) DEFAULT NULL,
    `createName` varchar(45) DEFAULT NULL,
    `createTime` datetime    DEFAULT NULL,
    `updateName` varchar(45) DEFAULT NULL,
    `updateTime` datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `speedyteam`
(
    `id`              int NOT NULL AUTO_INCREMENT,
    `team`            varchar(45) DEFAULT NULL,
    `type`            varchar(45) DEFAULT NULL,
    `company`         varchar(45) DEFAULT NULL,
    `affiliatePerson` varchar(45) DEFAULT NULL,
    `affiliateTeam`   varchar(45) DEFAULT NULL,
    `createName`      varchar(45) DEFAULT NULL,
    `createTime`      datetime    DEFAULT NULL,
    `updateName`      varchar(45) DEFAULT NULL,
    `updateTime`      datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;