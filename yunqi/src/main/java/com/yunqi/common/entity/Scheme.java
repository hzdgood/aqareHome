package com.yunqi.common.entity;

import java.util.Date;

//用户方案
public class Scheme {
    private Integer id;
    private Integer projectId;
    private Integer productId;
    private String number; // 实际数量
    private String installNumber; //总安装数
    private String debugNumber; //总调试数
    private String notInstalled; //未装
    private String unregulated; //未调
    private String unissued; //未发
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInstallNumber() {
        return installNumber;
    }

    public void setInstallNumber(String installNumber) {
        this.installNumber = installNumber;
    }

    public String getDebugNumber() {
        return debugNumber;
    }

    public void setDebugNumber(String debugNumber) {
        this.debugNumber = debugNumber;
    }

    public String getNotInstalled() {
        return notInstalled;
    }

    public void setNotInstalled(String notInstalled) {
        this.notInstalled = notInstalled;
    }

    public String getUnregulated() {
        return unregulated;
    }

    public void setUnregulated(String unregulated) {
        this.unregulated = unregulated;
    }

    public String getUnissued() {
        return unissued;
    }

    public void setUnissued(String unissued) {
        this.unissued = unissued;
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
