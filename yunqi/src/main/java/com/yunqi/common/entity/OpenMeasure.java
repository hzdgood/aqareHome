package com.yunqi.common.entity;

public class OpenMeasure {
    private Integer id;
    private Integer projectId;
    private Integer techId;
    private String projectName;
    private String type;
    private String area;// 区域
    private String motorModel;   // 电机型号
    private String trackType;  // 轨道类型
    private String installMethod;  // 安装方式
    private String number;  // 数量
    private String openMethod; // 开合方式
    private String powerPosition;  // 电源位置
    private String boxWidth;  // 窗帘盒宽
    private String surfaceMaterial; // 安装面材质
    private String placeholder; // 预留位置
    private String deductionInfo; // 扣减说明
    private String imgUrl;
    private String disabled;

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

    public String getMotorModel() {
        return motorModel;
    }

    public void setMotorModel(String motorModel) {
        this.motorModel = motorModel;
    }

    public String getTrackType() {
        return trackType;
    }

    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }

    public String getInstallMethod() {
        return installMethod;
    }

    public void setInstallMethod(String installMethod) {
        this.installMethod = installMethod;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOpenMethod() {
        return openMethod;
    }

    public void setOpenMethod(String openMethod) {
        this.openMethod = openMethod;
    }

    public String getPowerPosition() {
        return powerPosition;
    }

    public void setPowerPosition(String powerPosition) {
        this.powerPosition = powerPosition;
    }

    public String getBoxWidth() {
        return boxWidth;
    }

    public void setBoxWidth(String boxWidth) {
        this.boxWidth = boxWidth;
    }

    public String getSurfaceMaterial() {
        return surfaceMaterial;
    }

    public void setSurfaceMaterial(String surfaceMaterial) {
        this.surfaceMaterial = surfaceMaterial;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getDeductionInfo() {
        return deductionInfo;
    }

    public void setDeductionInfo(String deductionInfo) {
        this.deductionInfo = deductionInfo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
}
