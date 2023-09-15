package com.yunqi.common.entity;

import java.util.Date;

//用户方案
public class Scheme {
    private Integer id;
    private Integer projectId;
    private Integer productId;
    private Integer number; // 实际数量
    private Integer installNumber; //已装
    private Integer debugNumber; //已调
    private Integer notInstalled; //未装
    private Integer unregulated; //未调
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

    public Integer getInstallNumber() {
        return installNumber;
    }

    public void setInstallNumber(Integer installNumber) {
        this.installNumber = installNumber;
    }

    public Integer getDebugNumber() {
        return debugNumber;
    }

    public void setDebugNumber(Integer debugNumber) {
        this.debugNumber = debugNumber;
    }

    public Integer getNotInstalled() {
        return notInstalled;
    }

    public void setNotInstalled(Integer notInstalled) {
        this.notInstalled = notInstalled;
    }

    public Integer getUnregulated() {
        return unregulated;
    }

    public void setUnregulated(Integer unregulated) {
        this.unregulated = unregulated;
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
