package com.yunqi.common.view;

public class SchemeView {
    private Integer projectId;
    private Integer schemeId;
    private String salesName; //销售
    private String techName; //负责人
    private String projectName;// 姓名
    private String productName; //产品
    private Integer number; // 实际数量
    private Integer installNumber; //已安装
    private Integer debugNumber; //已调试
    private Integer notInstalled; //未装
    private Integer unregulated; //未调
    private Double head; //负责人
    private Double headDoor; // 负责人 - 上门
    private Double headDisclose; // 负责人-交底

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
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

    public Double getHead() {
        return head;
    }

    public void setHead(Double head) {
        this.head = head;
    }

    public Double getHeadDoor() {
        return headDoor;
    }

    public void setHeadDoor(Double headDoor) {
        this.headDoor = headDoor;
    }

    public Double getHeadDisclose() {
        return headDisclose;
    }

    public void setHeadDisclose(Double headDisclose) {
        this.headDisclose = headDisclose;
    }
}
