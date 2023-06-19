package com.aqara.common.entity;

import java.util.Date;

public class WorkSheet {
    private Integer id;
    private String itemId;
    private String custom; // 用户
    private String technology; // 上门技术
    private String actualWork; // 实际工时
    private Date orderDate;  // 订单日期
    private Date dateOfVisit; // 上门日期
    private Date completeTime; // 完成时间
    private String completeImage; // 完成照片
    private String signInImage; //签到水印
    private String handSignIn; //手工签到
    private Date signInTime; // 签到时间
    private String orderStr;
    private String dateOfStr;
    private String completeStr;
    private String signInStr;
    private String workType; // 工单类型
    private String workStatus; //工单状态
    private String userName; // 技术
    private String engName; // 技术英文
    private String count; //数量--视图
    private String area; // 区域
    private String restDay; //技术休息日
    private String disabled; //有效
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getActualWork() {
        return actualWork;
    }

    public void setActualWork(String actualWork) {
        this.actualWork = actualWork;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getCompleteImage() { return completeImage; }

    public void setCompleteImage(String completeImage) { this.completeImage = completeImage; }

    public String getSignInImage() { return signInImage; }

    public void setSignInImage(String signInImage) { this.signInImage = signInImage; }

    public String getHandSignIn() { return handSignIn; }

    public void setHandSignIn(String handSignIn) { this.handSignIn = handSignIn; }

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public String getDateOfStr() {
        return dateOfStr;
    }

    public void setDateOfStr(String dateOfStr) {
        this.dateOfStr = dateOfStr;
    }

    public String getCompleteStr() {
        return completeStr;
    }

    public void setCompleteStr(String completeStr) {
        this.completeStr = completeStr;
    }

    public String getSignInStr() {
        return signInStr;
    }

    public void setSignInStr(String signInStr) {
        this.signInStr = signInStr;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRestDay() {
        return restDay;
    }

    public void setRestDay(String restDay) {
        this.restDay = restDay;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
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
