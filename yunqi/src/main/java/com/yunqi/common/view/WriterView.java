package com.yunqi.common.view;

import java.util.Date;

public class WriterView {
    private Integer projectId; //项目Id
    private Integer schemeId; // 方案Id
    private Integer workId; // 工单Id
    private Integer productId; // 产品Id
    private String projectName; //项目名称
    private String productName;  //产品名称
    private String techName;  // 技术
    private String type; //类型
    private String status; // 状态
    private Date writerTime;
    private Integer number; // 数量
    private Integer installNumber; //已装
    private Integer debugNumber; //已调
    private Integer notInstalled; //未装
    private Integer unregulated; //未调

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

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getWriteTime() {
        return writerTime;
    }

    public void setWriteTime(Date writerTime) {
        this.writerTime = writerTime;
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
}
