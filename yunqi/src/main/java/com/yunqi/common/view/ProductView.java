package com.yunqi.common.view;

public class ProductView {
    private Integer workId;
    private Integer projectId;
    private Integer productId;
    private String techIds;
    private Integer customerInstall;
    private Integer customerDebug;
    private String type;
    private String productName;
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

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
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

    public String getTechIds() {
        return techIds;
    }

    public void setTechIds(String techIds) {
        this.techIds = techIds;
    }

    public Integer getCustomerInstall() {
        return customerInstall;
    }

    public void setCustomerInstall(Integer customerInstall) {
        this.customerInstall = customerInstall;
    }

    public Integer getCustomerDebug() {
        return customerDebug;
    }

    public void setCustomerDebug(Integer customerDebug) {
        this.customerDebug = customerDebug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
