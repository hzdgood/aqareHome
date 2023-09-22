package com.yunqi.common.entity;

import java.util.Date;

public class Commission {
    private Integer id;
    private String category; //品类
    private double cost; //技术成本
    private double installRatio; //安装比例
    private double debugRatio; //调试比例
    private double install;//安装
    private double debug;//调试
    private double disclose;//交底
    private double check;//验收
    private double headDoor;//负责人-上门
    private double headDisclose;//负责人-交底
    private double installContribute;//安装贡献
    private double debugContribute;//调试贡献
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getInstallRatio() {
        return installRatio;
    }

    public void setInstallRatio(double installRatio) {
        this.installRatio = installRatio;
    }

    public double getDebugRatio() {
        return debugRatio;
    }

    public void setDebugRatio(double debugRatio) {
        this.debugRatio = debugRatio;
    }

    public double getInstall() {
        return install;
    }

    public void setInstall(double install) {
        this.install = install;
    }

    public double getDebug() {
        return debug;
    }

    public void setDebug(double debug) {
        this.debug = debug;
    }

    public double getDisclose() {
        return disclose;
    }

    public void setDisclose(double disclose) {
        this.disclose = disclose;
    }

    public double getCheck() {
        return check;
    }

    public void setCheck(double check) {
        this.check = check;
    }

    public double getHeadDoor() {
        return headDoor;
    }

    public void setHeadDoor(double headDoor) {
        this.headDoor = headDoor;
    }

    public double getHeadDisclose() {
        return headDisclose;
    }

    public void setHeadDisclose(double headDisclose) {
        this.headDisclose = headDisclose;
    }

    public double getInstallContribute() {
        return installContribute;
    }

    public void setInstallContribute(double installContribute) {
        this.installContribute = installContribute;
    }

    public double getDebugContribute() {
        return debugContribute;
    }

    public void setDebugContribute(double debugContribute) {
        this.debugContribute = debugContribute;
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
