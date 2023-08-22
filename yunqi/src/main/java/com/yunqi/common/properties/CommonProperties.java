package com.yunqi.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "common")
public class CommonProperties {
    private String serviceUrl;
    private String localFiles;
    private String imgFiles;

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getLocalFiles() {
        return localFiles;
    }

    public void setLocalFiles(String localFiles) {
        this.localFiles = localFiles;
    }

    public String getImgFiles() {
        return imgFiles;
    }

    public void setImgFiles(String imgFiles) {
        this.imgFiles = imgFiles;
    }
}
