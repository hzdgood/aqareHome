package com.yunqi.common.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkTime {
    private Integer id;
    private Integer workId; // 工单
    private Integer projectId; // 项目
    private String techId; // 技术
    private String status; // 状态
    private Date signTime; //签到时间
    private Date departureTime;//离开时间
    private Date writerTime; // 核销时间
    private String standardTime;
    private String disabled;
    private String createName;
    private Date createTime;
    private String updateName;
    private Date updateTime;
    private String worksheetItem;
    private String itemId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTechId() {
        return techId;
    }

    public void setTechId(String techId) {
        this.techId = techId;
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

    public void setSignTime(String signTime) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.signTime = sf.parse(signTime);
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.departureTime = sf.parse(departureTime);
    }

    public Date getWriterTime() {
        return writerTime;
    }

    public void setWriterTime(Date writerTime) {
        this.writerTime = writerTime;
    }

    public String getStandardTime() {
        return standardTime;
    }

    public void setStandardTime(String standardTime) {
        this.standardTime = standardTime;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
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

    public String getWorksheetItem() {
        return worksheetItem;
    }

    public void setWorksheetItem(String worksheetItem) {
        this.worksheetItem = worksheetItem;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
