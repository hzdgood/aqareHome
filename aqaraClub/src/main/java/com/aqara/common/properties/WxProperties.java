package com.aqara.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx")
public class WxProperties {
	private String corpId;
	private String corpSecret;
	private String httpUrl;
	private String userIdUrl;
	private String jsapiUrl;
	private String appUrl;
	private String externalcontact;
	private String externalList;
	private String groupchat;
	private String groupList;
	private String permitlist;
	private String checkAgree;

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

	public String getExternalcontact() {
		return externalcontact;
	}

	public void setExternalcontact(String externalcontact) {
		this.externalcontact = externalcontact;
	}

	public String getGroupchat() {
		return groupchat;
	}

	public void setGroupchat(String groupchat) {
		this.groupchat = groupchat;
	}

	public String getExternalList() {
		return externalList;
	}

	public void setExternalList(String externalList) {
		this.externalList = externalList;
	}

	public String getGroupList() {
		return groupList;
	}

	public void setGroupList(String groupList) {
		this.groupList = groupList;
	}

	public String getPermitlist() {
		return permitlist;
	}

	public void setPermitlist(String permitlist) {
		this.permitlist = permitlist;
	}

	public String getCheckAgree() {
		return checkAgree;
	}

	public void setCheckAgree(String checkAgree) {
		this.checkAgree = checkAgree;
	}

}
