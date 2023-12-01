package com.yunqi.common.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private String disabled; //有效
    private String workSummary; //今日工作总结
    private String visitNode; //下次上门节点
    private String handover; // 工作交接信息
    private String standardTime; // 额定工时
    private String remark;
    private String createName;
    private Date createTime;
    private String updateName;
    private Date updateTime;
    private String projectItem;
    private String itemId;

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

    public void setDateOfVisit(String dateOfVisit) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.dateOfVisit = sf.parse(dateOfVisit);
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

    public String getHandover() {
        return handover;
    }

    public void setHandover(String handover) {
        this.handover = handover;
    }

    public String getStandardTime() {
        return standardTime;
    }

    public void setStandardTime(String standardTime) {
        this.standardTime = standardTime;
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

    public String getProjectItem() {
        return projectItem;
    }

    public void setProjectItem(String projectItem) {
        this.projectItem = projectItem;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
