package com.aqara.common.entity;

import java.util.Date;

public class Customer {
	private Integer id;
	private String companyId; //公司
	private String name; //客户姓名
	private String telephone; // 客户电话
	private String sales; //销售
	private String department; //部门
	private String sex; // 性别
	private String source; //客户来源
	private String houseType; //房型
	private String houseState; //房屋情况
	private String personnelAttr; //人员属性
	private String customerAttr; //客户属性
	private String customerStage; //客户阶段
	private String SPcustomer; //单品客户
	private String houseDemand; //全屋需求
	private String lossStatus; //流失状态
	private String weiXin; //微信
	private String UserID;
	private String releWeixin; //关联企微
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getHouseState() {
		return houseState;
	}

	public void setHouseState(String houseState) {
		this.houseState = houseState;
	}

	public String getPersonnelAttr() {
		return personnelAttr;
	}

	public void setPersonnelAttr(String personnelAttr) {
		this.personnelAttr = personnelAttr;
	}

	public String getCustomerAttr() {
		return customerAttr;
	}

	public void setCustomerAttr(String customerAttr) {
		this.customerAttr = customerAttr;
	}

	public String getCustomerStage() {
		return customerStage;
	}

	public void setCustomerStage(String customerStage) {
		this.customerStage = customerStage;
	}

	public String getSPcustomer() {
		return SPcustomer;
	}

	public void setSPcustomer(String sPcustomer) {
		SPcustomer = sPcustomer;
	}

	public String getHouseDemand() {
		return houseDemand;
	}

	public void setHouseDemand(String houseDemand) {
		this.houseDemand = houseDemand;
	}

	public String getLossStatus() {
		return lossStatus;
	}

	public void setLossStatus(String lossStatus) {
		this.lossStatus = lossStatus;
	}

	public String getWeiXin() {
		return weiXin;
	}

	public void setWeiXin(String weiXin) {
		this.weiXin = weiXin;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getReleWeixin() {
		return releWeixin;
	}

	public void setReleWeixin(String releWeixin) {
		this.releWeixin = releWeixin;
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", companyId=" + companyId + ", name=" + name + ", telephone=" + telephone
				+ ", sales=" + sales + ", department=" + department + ", sex=" + sex + ", source=" + source
				+ ", houseType=" + houseType + ", houseState=" + houseState + ", personnelAttr=" + personnelAttr
				+ ", customerAttr=" + customerAttr + ", customerStage=" + customerStage + ", SPcustomer=" + SPcustomer
				+ ", houseDemand=" + houseDemand + ", lossStatus=" + lossStatus + ", weiXin=" + weiXin + ", UserID="
				+ UserID + ", releWeixin=" + releWeixin + ", createName=" + createName + ", createTime=" + createTime
				+ ", updateName=" + updateName + ", updateTime=" + updateTime + "]";
	}
	
}
