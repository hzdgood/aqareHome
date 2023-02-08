package com.aqara.common.entity;

import java.util.Date;

public class SpeedyContent {
    private Integer id;
    private String mediaId; //企业微信ID
    private Integer teamId;
    private Integer themeId;
    private String type;
    private String team;
    private String theme;
    private String affiliatePerson;
    private String company;
    private String contentTitle; //标题
    private String contentText; //内容
    private String contentImage; //图片
    private String contentVideo; //视频
    private String contentFile; //文件
    private String contentType; //类型
    private String contentLevel; //内容等级 常用，不常用
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

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getAffiliatePerson() {
        return affiliatePerson;
    }

    public void setAffiliatePerson(String affiliatePerson) {
        this.affiliatePerson = affiliatePerson;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public String getContentImage() { return contentImage; }

    public void setContentImage(String contentImage) { this.contentImage = contentImage; }

    public String getContentVideo() { return contentVideo; }

    public void setContentVideo(String contentVideo) { this.contentVideo = contentVideo; }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentFile() {
        return contentFile;
    }

    public void setContentFile(String contentFile) {
        this.contentFile = contentFile;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentLevel() {
        return contentLevel;
    }

    public void setContentLevel(String contentLevel) {
        this.contentLevel = contentLevel;
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
