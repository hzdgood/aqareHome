package com.aqara.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx")
public class WxProperties {
	private String corpId;
	private String corpSecret;
	private String corpHttp;
	private String speedySecret;
	private String speedyHttp;
	private String httpUrl;
	private String userIdUrl;
	private String jsapiUrl;
	private String appUrl;
	private String externalContact;
	private String externalList;
	private String groupChat;
	private String groupList;
	private String permitList;
	private String checkAgree;
	private String calendarAdd;
	private String scheduleAdd;
	private String mediaUpload;
	private String compUser;

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getCorpSecret() {
		return corpSecret;
	}

	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}

	public String getCorpHttp() {
		return corpHttp;
	}

	public void setCorpHttp(String corpHttp) {
		this.corpHttp = corpHttp;
	}

	public String getSpeedySecret() {
		return speedySecret;
	}

	public void setSpeedySecret(String speedySecret) {
		this.speedySecret = speedySecret;
	}

	public String getSpeedyHttp() {
		return speedyHttp;
	}

	public void setSpeedyHttp(String speedyHttp) {
		this.speedyHttp = speedyHttp;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public String getUserIdUrl() {
		return userIdUrl;
	}

	public void setUserIdUrl(String userIdUrl) {
		this.userIdUrl = userIdUrl;
	}

	public String getJsapiUrl() {
		return jsapiUrl;
	}

	public void setJsapiUrl(String jsapiUrl) {
		this.jsapiUrl = jsapiUrl;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getExternalContact() {
		return externalContact;
	}

	public void setExternalContact(String externalContact) {
		this.externalContact = externalContact;
	}

	public String getExternalList() {
		return externalList;
	}

	public void setExternalList(String externalList) {
		this.externalList = externalList;
	}

	public String getGroupChat() {
		return groupChat;
	}

	public void setGroupChat(String groupChat) {
		this.groupChat = groupChat;
	}

	public String getGroupList() {
		return groupList;
	}

	public void setGroupList(String groupList) {
		this.groupList = groupList;
	}

	public String getPermitList() {
		return permitList;
	}

	public void setPermitList(String permitList) {
		this.permitList = permitList;
	}

	public String getCheckAgree() {
		return checkAgree;
	}

	public void setCheckAgree(String checkAgree) {
		this.checkAgree = checkAgree;
	}

	public String getCalendarAdd() {
		return calendarAdd;
	}

	public void setCalendarAdd(String calendarAdd) {
		this.calendarAdd = calendarAdd;
	}

	public String getScheduleAdd() {
		return scheduleAdd;
	}

	public void setScheduleAdd(String scheduleAdd) {
		this.scheduleAdd = scheduleAdd;
	}

	public String getMediaUpload() {
		return mediaUpload;
	}

	public void setMediaUpload(String mediaUpload) {
		this.mediaUpload = mediaUpload;
	}

	public String getCompUser() {
		return compUser;
	}

	public void setCompUser(String compUser) {
		this.compUser = compUser;
	}
}
