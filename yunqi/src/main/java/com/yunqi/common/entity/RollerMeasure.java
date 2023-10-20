package com.yunqi.common.entity;

public class RollerMeasure {
    private Integer id;
    private Integer projectId;
    private String area; // 区域
    private String rollingScheme; // 卷帘方案
    private String length; // 长度
    private String high; // 高度
    private String surfaceWidth; // 安装面宽度
    private String installMethod;  // 安装方式
    private String deductionInfo;  // 扣减说明
    private String number;// 数量
    private String surfaceMaterial;  // 安装面材质
    private String powerPosition;   // 电源位置
    private String cover; // 需要罩壳

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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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
}
