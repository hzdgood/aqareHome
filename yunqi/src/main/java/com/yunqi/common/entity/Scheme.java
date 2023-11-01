package com.yunqi.common.entity;

import java.util.Date;

//用户方案
public class Scheme {
    private Integer id;
    private Integer projectId;
    private Integer productId;
    private Integer number; // 实际数量
    private double installNumber; //已装
    private double debugNumber; //已调
    private double notInstalled; //未装
    private double unregulated; //未调
    private Integer notSent; //未发
    private String itemId;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public double getInstallNumber() {
        return installNumber;
    }

    public void setInstallNumber(double installNumber) {
        this.installNumber = installNumber;
    }

    public double getDebugNumber() {
        return debugNumber;
    }

    public void setDebugNumber(double debugNumber) {
        this.debugNumber = debugNumber;
    }

    public double getNotInstalled() {
        return notInstalled;
    }

    public void setNotInstalled(double notInstalled) {
        this.notInstalled = notInstalled;
    }

    public double getUnregulated() {
        return unregulated;
    }

    public void setUnregulated(double unregulated) {
        this.unregulated = unregulated;
    }

    public Integer getNotSent() {
        return notSent;
    }

    public void setNotSent(Integer notSent) {
        this.notSent = notSent;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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
