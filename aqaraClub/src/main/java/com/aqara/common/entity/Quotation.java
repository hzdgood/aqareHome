package com.aqara.common.entity;

import java.util.Date;

public class Quotation {
	private Integer id;
	private String code; // 项目
	private String quotationType; // 报价单类型
	private String quotationID; // 报价单ID
	private String schemeAmount; // 方案金额
	private String preferentialAmount; // 优惠金额
	private String amountReceivable; // 应收金额
	private String amountReceived; // 已收金额
	private String uncollectedAmount; // 未收金额
	private String remarks; // 备注
	private String uploadContract; // 上传合同
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
	public String getQuotationType() {
		return quotationType;
	}
	public void setQuotationType(String quotationType) {
		this.quotationType = quotationType;
	}
	public String getQuotationID() {
		return quotationID;
	}
	public void setQuotationID(String quotationID) {
		this.quotationID = quotationID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSchemeAmount() {
		return schemeAmount;
	}
	public void setSchemeAmount(String schemeAmount) {
		this.schemeAmount = schemeAmount;
	}
	public String getPreferentialAmount() {
		return preferentialAmount;
	}
	public void setPreferentialAmount(String preferentialAmount) {
		this.preferentialAmount = preferentialAmount;
	}
	public String getAmountReceivable() {
		return amountReceivable;
	}
	public void setAmountReceivable(String amountReceivable) {
		this.amountReceivable = amountReceivable;
	}
	public String getAmountReceived() {
		return amountReceived;
	}
	public void setAmountReceived(String amountReceived) {
		this.amountReceived = amountReceived;
	}
	public String getUncollectedAmount() {
		return uncollectedAmount;
	}
	public void setUncollectedAmount(String uncollectedAmount) {
		this.uncollectedAmount = uncollectedAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getUploadContract() {
		return uploadContract;
	}
	public void setUploadContract(String uploadContract) {
		this.uploadContract = uploadContract;
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
