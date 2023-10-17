package com.yunqi.common.view;

import java.util.Date;

public class DetailView { //核销记录统计
    private Integer headId; // 负责人
    private String techId; // 技术
    private String projectName; // 项目
    private String productName; // 产品
    private String sheetName; // 工单名称
    private String techName; // 技术
    private String type; //  类型
    private Integer number; // 数量
    private String contribution; // 贡献度
    private Date writerTime;

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public String getTechId() {
        return techId;
    }

    public void setTechId(String techId) {
        this.techId = techId;
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

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public Date getWriterTime() {
        return writerTime;
    }

    public void setWriterTime(Date writerTime) {
        this.writerTime = writerTime;
    }
}
