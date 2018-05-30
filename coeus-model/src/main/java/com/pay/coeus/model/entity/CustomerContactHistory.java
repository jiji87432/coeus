package com.pay.coeus.model.entity;

import java.util.Date;

public class CustomerContactHistory extends BaseEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4596266219023542191L;
	private String customerNo;
	private String customerRole;
	private String phone;
	private String source;
	private Date createTime;
	private String remark;
	
	public CustomerContactHistory() {
		super();
	}

	public CustomerContactHistory(String customerNo, String customerRole, String phone, String source, Date createTime, String remark) {
		super();
		this.customerNo = customerNo;
		this.customerRole = customerRole;
		this.phone = phone;
		this.source = source;
		this.createTime = createTime;
		this.remark = remark;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(String customerRole) {
		this.customerRole = customerRole;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "CustomerContactInfo [customerNo=" + customerNo + ", customerRole=" + customerRole + ", phone=" + phone
				+ ", source=" + source + ", createTime=" + createTime + ", remark=" + remark + "]";
	}

}
