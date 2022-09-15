package com.aqara.common.entity;

import java.util.Date;

public class Project {
	private Integer id;
	private String code; // 项目编码
	private String currrentTask; // 目前任务
	private String decorationProgress; // 装修进度
	private String saleConfirm; // 销售确认
	private String end;// 完结
	private String projectName;// 项目姓名
	private String projectTelephone;// 项目电话
	private String projectType;// 项目类型
	private String mainProject;// 主项目
	private String groupID;// 群ID
	private String sales; // 销售
	private String department;
	private String itemId;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCurrrentTask() {
		return currrentTask;
	}

	public void setCurrrentTask(String currrentTask) {
		this.currrentTask = currrentTask;
	}

	public String getDecorationProgress() {
		return decorationProgress;
	}

	public void setDecorationProgress(String decorationProgress) {
		this.decorationProgress = decorationProgress;
	}

	public String getSaleConfirm() {
		return saleConfirm;
	}

	public void setSaleConfirm(String saleConfirm) {
		this.saleConfirm = saleConfirm;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectTelephone() {
		return projectTelephone;
	}

	public void setProjectTelephone(String projectTelephone) {
		this.projectTelephone = projectTelephone;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMainProject() {
		return mainProject;
	}

	public void setMainProject(String mainProject) {
		this.mainProject = mainProject;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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
