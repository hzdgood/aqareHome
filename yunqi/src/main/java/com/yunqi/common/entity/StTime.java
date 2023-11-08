package com.yunqi.common.entity;

public class StTime {
    private Integer projectId;
    private String projectName;
    private Double stInstall;
    private Double stDebug;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getStInstall() {
        return stInstall;
    }

    public void setStInstall(double stInstall) {
        this.stInstall = stInstall;
    }

    public double getStDebug() {
        return stDebug;
    }

    public void setStDebug(double stDebug) {
        this.stDebug = stDebug;
    }
}
