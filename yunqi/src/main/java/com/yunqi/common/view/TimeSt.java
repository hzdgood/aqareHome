package com.yunqi.common.view;

import java.util.Date;

public class TimeSt {
    private String name;
    private String waitday;
    private Date dateOfVisit;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWaitday() {
        return waitday;
    }

    public void setWaitday(String waitday) {
        this.waitday = waitday;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
