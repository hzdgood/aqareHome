CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `companyId` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `sales` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `houseType` varchar(45) DEFAULT NULL,
  `houseState` varchar(45) DEFAULT NULL,
  `personnelAttr` varchar(45) DEFAULT NULL,
  `customerAttr` varchar(45) DEFAULT NULL,
  `customerStage` varchar(45) DEFAULT NULL,
  `SPcustomer` varchar(45) DEFAULT NULL,
  `houseDemand` varchar(45) DEFAULT NULL,
  `lossStatus` varchar(45) DEFAULT NULL,
  `weiXin` varchar(45) DEFAULT NULL,
  `UserID` varchar(300) DEFAULT NULL,
  `releWeixin` varchar(45) DEFAULT NULL,
  `createName` varchar(45) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateName` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `openCurtain` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `sales` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `supplier` datetime DEFAULT NULL,
  `merchantConfirm` varchar(45) DEFAULT NULL,
  `createName` varchar(45) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateName` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `rollerShutter` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `sales` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `supplier` datetime DEFAULT NULL,
  `merchantConfirm` varchar(45) DEFAULT NULL,
  `createName` varchar(45) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateName` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `survey` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `sales` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `appointmentTime` datetime DEFAULT NULL,
  `estimatedDuration` varchar(45) DEFAULT NULL,
  `participants` varchar(45) DEFAULT NULL,
  `surveyPictures` varchar(150) DEFAULT NULL,
  `surveyTask` varchar(150) DEFAULT NULL,
  `createName` varchar(45) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateName` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `quotation` (
 `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `sales` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `quotationType` varchar(45) DEFAULT NULL,
  `quotationID` varchar(45) DEFAULT NULL,
  `schemeAmount` varchar(45) DEFAULT NULL,
  `preferentialAmount` varchar(45) DEFAULT NULL,
  `amountReceivable` varchar(45) DEFAULT NULL,
  `amountReceived` varchar(45) DEFAULT NULL,
  `uncollectedAmount` varchar(45) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  `uploadContract` varchar(45) DEFAULT NULL,
  `createName` varchar(45) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateName` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `collent` (
 `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `sales` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `projectStatus` varchar(45) DEFAULT NULL,
  `totalReceipts` varchar(45) DEFAULT NULL,
  `finalPrice` varchar(45) DEFAULT NULL,
  `changePrice` varchar(45) DEFAULT NULL,
  `collectionStatus` varchar(45) DEFAULT NULL,
  `refundType` varchar(45) DEFAULT NULL,
  `paymentMethod` varchar(45) DEFAULT NULL,
  `collection` varchar(45) DEFAULT NULL,
  `quotationID` varchar(45) DEFAULT NULL,
  `documentDate` datetime DEFAULT NULL,
  `settlementDate` datetime DEFAULT NULL,
  `uploadVoucher` varchar(45) DEFAULT NULL,
  `payee` varchar(45) DEFAULT NULL,
  `crossCheck` varchar(45) DEFAULT NULL,
  `createName` varchar(45) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateName` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `project` (
 `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `sales` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `currrentTask` varchar(45) DEFAULT NULL,
  `decorationProgress` varchar(45) DEFAULT NULL,
  `saleConfirm` varchar(45) DEFAULT NULL,
  `deliveryDegree` varchar(45) DEFAULT NULL,
  `completDegree` varchar(45) DEFAULT NULL,
  `projectName` varchar(45) DEFAULT NULL,
  `projectTelephone` varchar(45) DEFAULT NULL,
  `projectType` varchar(45) DEFAULT NULL,
  `mainProject` varchar(45) DEFAULT NULL,
  `groupID` varchar(45) DEFAULT NULL,
  `fullPdf` varchar(45) DEFAULT NULL,
  `schemePicture` varchar(150) DEFAULT NULL,
  `systemSelect` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `area` varchar(45) DEFAULT NULL,
  `village` varchar(45) DEFAULT NULL,
  `createName` varchar(45) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateName` varchar(45) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `huoban` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ticket` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `expired` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `custom` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `info` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `scheme` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderNumber` varchar(45) DEFAULT NULL,
  `customerName` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `productName` varchar(45) DEFAULT NULL,
  `productCode` varchar(45) DEFAULT NULL,
  `number` varchar(45) DEFAULT NULL,
  `money` varchar(45) DEFAULT NULL,
  `yesNoFee` varchar(45) DEFAULT NULL,
  `serviceFee` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=473 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) DEFAULT NULL,
  `passWord` varchar(50) DEFAULT NULL,
  `userId` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3


CREATE TABLE `wechat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ticket` varchar(300) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `expired` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci