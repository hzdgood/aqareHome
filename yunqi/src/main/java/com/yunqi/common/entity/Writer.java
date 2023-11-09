package com.yunqi.common.entity;

import java.text.DecimalFormat;
import java.util.Date;

public class Writer {
    private Integer id;
    private Integer techId; // 技术
    private Integer projectId; // 项目
    private Integer productId; // 产品
    private Integer schemeId; // 方案
    private Integer workId; // 工单ID
    private double install; // 调试
    private double debug; // 安装
    private double sumWork; // 总核销
    private String type; // 类型
    private double contribution; // 贡献度
    private String createName;
    private Date createTime;
    private String updateName;
    private Date updateTime;
    private String disabled; //有效
    private String techError; // 技术报错

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTechId() {
        return techId;
    }

    public void setTechId(Integer techId) {
        this.techId = techId;
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

    public double getSumWork() {
        return sumWork;
    }

    public void setSumWork(double sumWork) {
        this.sumWork = sumWork;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(double contribution) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        this.contribution = Double.parseDouble(decimalFormat.format(contribution));
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

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getTechError() {
        return techError;
    }

    public void setTechError(String techError) {
        this.techError = techError;
    }
}
