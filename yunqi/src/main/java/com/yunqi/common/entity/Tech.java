package com.yunqi.common.entity;

public class Tech {
    private Integer id;
    private String name;
    private String telephone;
    private String status;
    private String position; //职位
    private String disabled; // 可见
    private String waitDay; // 休息日

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getWaitDay() {
        return waitDay;
    }

    public void setWaitDay(String waitDay) {
        this.waitDay = waitDay;
    }
}
