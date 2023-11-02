package com.yunqi.common.entity;

import java.util.Date;

public class SyncScheme {
    private Integer id;
    private String projectId;
    private String productId;
    private String number; // 实际数量
    private String notInstalled; //未装
    private String unregulated; //未调
    private String notSent; //未发
    private String itemId;
    private String disabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getNotSent() {
        return notSent;
    }

    public void setNotSent(String notSent) {
        this.notSent = notSent;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
