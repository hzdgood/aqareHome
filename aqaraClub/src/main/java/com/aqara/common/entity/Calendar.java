package com.aqara.common.entity;

public class Calendar {
	private String organizer;
	private String userid;
	private String summary;
	private String timestamp1;
	private String timestamp2;
	private String description;
	private String location;
	private String cal_id;
	private String ddid;
	private String yyx;
	private String type;
	private String tb;
	private String rw;

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTimestamp1() {
		return timestamp1;
	}

	public void setTimestamp1(String timestamp1) {
		this.timestamp1 = timestamp1;
	}

	public String getTimestamp2() {
		return timestamp2;
	}

	public void setTimestamp2(String timestamp2) {
		this.timestamp2 = timestamp2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCal_id() {
		return cal_id;
	}

	public void setCal_id(String cal_id) {
		this.cal_id = cal_id;
	}

	public String getDdid() {
		return ddid;
	}

	public void setDdid(String ddid) {
		this.ddid = ddid;
	}

	public String getYyx() {
		return yyx;
	}

	public void setYyx(String yyx) {
		this.yyx = yyx;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTb() {
		return tb;
	}

	public void setTb(String tb) {
		this.tb = tb;
	}

	public String getRw() {
		return rw;
	}

	public void setRw(String rw) {
		this.rw = rw;
	}

	@Override
	public String toString() {
		return "Calendar [organizer=" + organizer + ", userid=" + userid + ", summary=" + summary + ", timestamp1="
				+ timestamp1 + ", timestamp2=" + timestamp2 + ", description=" + description + ", location=" + location
				+ ", cal_id=" + cal_id + ", ddid=" + ddid + ", yyx=" + yyx + ", type=" + type + ", tb=" + tb + ", rw="
				+ rw + "]";
	}
	
}
