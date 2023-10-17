package com.yunqi.common.view;

import java.util.Date;

public class WorkView {
    private Integer timeId;
    private Integer workId;
    private Integer projectId;
    private String techIds; //技术
    private String headId; //项目负责人
    private String headName; // 工单负责人
    private String projectName;// 姓名
    private String telephone;// 电话
    private String address; // 地址
    private String techNames; // 技术
    private String salesName; // 销售
    private Date dateOfVisit; // 上门日期
    private String workStatus;
    private String status; // 工单状态
    private Date signTime; //签到时间
    private Date departureTime;//离开时间
    private String techId;
    private String techName; // 技术
    private String type; //类型
    private String actualWork; // 工时
    private String schedule; //进度
    private String workSummary; // 今日完成情况
    private String visitNode; //下次工作安排
    private String disabled; //有效

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

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

    public String getTechIds() {
        return techIds;
    }

    public void setTechIds(String techIds) {
        this.techIds = techIds;
    }

    public String getHeadId() {
        return headId;
    }

    public void setHeadId(String headId) {
        this.headId = headId;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTechNames() {
        return techNames;
    }

    public void setTechNames(String techNames) {
        this.techNames = techNames;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getTechId() {
        return techId;
    }

    public void setTechId(String techId) {
        this.techId = techId;
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

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
