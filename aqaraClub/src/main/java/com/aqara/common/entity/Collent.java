package com.aqara.common.entity;

import java.util.Date;

public class Collent {
    private Integer id;
    private String code; // 项目编码
    private String sales; //销售
    private String department;
    private String projectStatus; // 当前项目状态
    private String totalReceipts; // 收款总额17
    private String finalPrice; // 最终价格18
    private String changePrice; // 变更差价19
    private String collectionStatus; // 收款状态22
    private String refundType; // 退款类型23
    private String paymentMethod; // 收款方式24
    private String collection; // 收款26
    private String quotationID; // 报价单ID*28
    private Date documentDate; // 单据日期*33
    private Date settlementDate; // 结算日期*34
    private String uploadVoucher; // 上传凭证*36
    private String payee; // 收款人*40
    private String crossCheck; // 是否勾稽*54
    private String createName;
    private Date createTime;
    private String updateName;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getTotalReceipts() {
        return totalReceipts;
    }

    public void setTotalReceipts(String totalReceipts) {
        this.totalReceipts = totalReceipts;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getQuotationID() {
        return quotationID;
    }

    public void setQuotationID(String quotationID) {
        this.quotationID = quotationID;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getUploadVoucher() {
        return uploadVoucher;
    }

    public void setUploadVoucher(String uploadVoucher) {
        this.uploadVoucher = uploadVoucher;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getCrossCheck() {
        return crossCheck;
    }

    public void setCrossCheck(String crossCheck) {
        this.crossCheck = crossCheck;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
