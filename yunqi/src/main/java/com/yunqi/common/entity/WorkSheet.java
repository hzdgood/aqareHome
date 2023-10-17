package com.yunqi.common.entity;

import java.util.Date;

public class WorkSheet {
    private Integer id;
    private Integer projectId;
    private String techIds;
    private String headName;
    private Date dateOfVisit; // 上门日期
    private String type; //类型
    private String status;
    private String actualWork; // 工时
    private String schedule; //进度
    private String writerImg; //核销图片
    private String reportImg; // 汇报图片
    private String materialImg; // 物料图片
    private String otherImg; // 其他图片
    private String signImg; //签字单图片
    private String disabled; //有效
    private String workSummary; //今日工作总结
    private String visitNode; //下次上门节点
    private String remark;
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

    public String getTechIds() {
        return techIds;
    }

    public void setTechIds(String techIds) {
        this.techIds = techIds;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
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

    public String getActualWork() {
        return actualWork;
    }

    public void setActualWork(String actualWork) {
        this.actualWork = actualWork;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getWriterImg() {
        return writerImg;
    }

    public void setWriterImg(String writerImg) {
        this.writerImg = writerImg;
    }

    public String getReportImg() {
        return reportImg;
    }

    public void setReportImg(String reportImg) {
        this.reportImg = reportImg;
    }

    public String getMaterialImg() {
        return materialImg;
    }

    public void setMaterialImg(String materialImg) {
        this.materialImg = materialImg;
    }

    public String getOtherImg() {
        return otherImg;
    }

    public void setOtherImg(String otherImg) {
        this.otherImg = otherImg;
    }

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getWorkSummary() {
        return workSummary;
    }

    public void setWorkSummary(String workSummary) {
        this.workSummary = workSummary;
    }

    public String getVisitNode() {
        return visitNode;
    }

    public void setVisitNode(String visitNode) {
        this.visitNode = visitNode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
