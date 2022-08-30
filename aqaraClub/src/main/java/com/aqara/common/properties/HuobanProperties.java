package com.aqara.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "huoban")
public class HuobanProperties {
	private String application_id;
	private String application_secret;
	private String ticket;
	private String expired;
	private String userInfo;
	private String SearchInfo;
	private String updateTable;
	private String addInfo;

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getApplication_secret() {
		return application_secret;
	}

	public void setApplication_secret(String application_secret) {
		this.application_secret = application_secret;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getSearchInfo() {
		return SearchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		SearchInfo = searchInfo;
	}

	public String getUpdateTable() {
		return updateTable;
	}

	public void setUpdateTable(String updateTable) {
		this.updateTable = updateTable;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
	
}
