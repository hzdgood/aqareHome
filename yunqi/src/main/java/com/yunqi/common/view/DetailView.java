package com.yunqi.common.view;

import java.util.Date;

public class DetailView { //核销记录统计
    private Integer id;
    private String projectName;
    private String productName;
    private String type;
    private Integer number;
    private Date writerTime;
    private String contribution;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getWriterTime() {
        return writerTime;
    }

    public void setWriterTime(Date writerTime) {
        this.writerTime = writerTime;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }
}
