package com.yunqi.common.entity;

import java.util.Date;

public class RollerMeasure {
    private Integer id;
    private Integer projectId;
    private Integer techId;
    private String projectName;
    private String type;
    private String area; // 区域
    private String rollingScheme; // 卷帘方案
    private Double length; // 长度
    private Double high; // 高度
    private String surfaceWidth; // 安装面宽度
    private String installMethod;  // 安装方式
    private String deductionInfo;  // 扣减说明
    private Integer number;// 数量
    private String surfaceMaterial;  // 安装面材质
    private String powerPosition;   // 电源位置
    private String cover; // 需要罩壳
    private String imgUrl;
    private String remark;
    private String disabled;
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

    public Integer getTechId() {
        return techId;
    }

    public void setTechId(Integer techId) {
        this.techId = techId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRollingScheme() {
        return rollingScheme;
    }

    public void setRollingScheme(String rollingScheme) {
        this.rollingScheme = rollingScheme;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public String getSurfaceWidth() {
        return surfaceWidth;
    }

    public void setSurfaceWidth(String surfaceWidth) {
        this.surfaceWidth = surfaceWidth;
    }

    public String getInstallMethod() {
        return installMethod;
    }

    public void setInstallMethod(String installMethod) {
        this.installMethod = installMethod;
    }

    public String getDeductionInfo() {
        return deductionInfo;
    }

    public void setDeductionInfo(String deductionInfo) {
        this.deductionInfo = deductionInfo;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSurfaceMaterial() {
        return surfaceMaterial;
    }

    public void setSurfaceMaterial(String surfaceMaterial) {
        this.surfaceMaterial = surfaceMaterial;
    }

    public String getPowerPosition() {
        return powerPosition;
    }

    public void setPowerPosition(String powerPosition) {
        this.powerPosition = powerPosition;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
