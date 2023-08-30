package com.yunqi.common.entity;

public class Commission {
    private Integer id;
    private String category; //品类
    private String cost; //技术成本
    private String installRatio; //安装比例
    private String debugRatio; //调试比例
    private String install;//安装
    private String debug;//调试
    private String disclose;//交底
    private String check;//验收
    private String headDoor;//负责人-上门
    private String headDisclose;//负责人-交底
    private String headComplete;//负责人-完结

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getInstallRatio() {
        return installRatio;
    }

    public void setInstallRatio(String installRatio) {
        this.installRatio = installRatio;
    }

    public String getDebugRatio() {
        return debugRatio;
    }

    public void setDebugRatio(String debugRatio) {
        this.debugRatio = debugRatio;
    }

    public String getInstall() {
        return install;
    }

    public void setInstall(String install) {
        this.install = install;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getDisclose() {
        return disclose;
    }

    public void setDisclose(String disclose) {
        this.disclose = disclose;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getHeadDoor() {
        return headDoor;
    }

    public void setHeadDoor(String headDoor) {
        this.headDoor = headDoor;
    }

    public String getHeadDisclose() {
        return headDisclose;
    }

    public void setHeadDisclose(String headDisclose) {
        this.headDisclose = headDisclose;
    }

    public String getHeadComplete() {
        return headComplete;
    }

    public void setHeadComplete(String headComplete) {
        this.headComplete = headComplete;
    }
}
