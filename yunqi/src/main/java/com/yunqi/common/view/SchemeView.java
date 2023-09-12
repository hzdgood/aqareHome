package com.yunqi.common.view;

public class SchemeView {
    private Integer salesName; //销售
    private Integer techName; //技术
    private String projectName;// 姓名
    private String productName; //产品
    private Integer number; // 实际数量
    private Integer installNumber; //总安装数
    private Integer debugNumber; //总调试数
    private Integer notInstalled; //未装
    private Integer unregulated; //未调
    private Integer unissued; //未发

    public Integer getSalesName() {
        return salesName;
    }

    public void setSalesName(Integer salesName) {
        this.salesName = salesName;
    }

    public Integer getTechName() {
        return techName;
    }

    public void setTechName(Integer techName) {
        this.techName = techName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Integer getUnissued() {
        return unissued;
    }

    public void setUnissued(Integer unissued) {
        this.unissued = unissued;
    }
}
