package com.aqara.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "reboot")
public class RebootProperties {
    private String customerBroadcast;
    private String projectBroadcast;

    public String getCustomerBroadcast() {
        return customerBroadcast;
    }

    public void setCustomerBroadcast(String customerBroadcast) {
        this.customerBroadcast = customerBroadcast;
    }

    public String getProjectBroadcast() {
        return projectBroadcast;
    }

    public void setProjectBroadcast(String projectBroadcast) {
        this.projectBroadcast = projectBroadcast;
    }

}
