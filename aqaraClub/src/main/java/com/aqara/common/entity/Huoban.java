package com.aqara.common.entity;

import java.util.Date;

//伙伴云
public class Huoban {
    private Integer id;
    private String ticket;
    private Date date;
    private String expired;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }
}
