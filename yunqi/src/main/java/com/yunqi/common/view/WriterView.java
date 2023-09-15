package com.yunqi.common.view;

import java.util.Date;

public class WriterView {
    private Integer headId; //项目负责人
    private Integer installNumber; // 安装数量
    private Integer debugNumber; // 调试数量
    private String projectName; //项目名称
    private String productName;  //产品名称
    private String techIds;  // 技术
    private String techName;  // 技术
    private String type; // 工单类型
    private Date completeTime; // 结算日期

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
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

    public String getTechIds() {
        return techIds;
    }

    public void setTechIds(String techIds) {
        this.techIds = techIds;
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

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }
}
