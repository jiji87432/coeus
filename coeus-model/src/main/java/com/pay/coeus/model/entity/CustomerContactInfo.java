package com.pay.coeus.model.entity;

import java.util.Date;

public class CustomerContactInfo extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8421639771656726810L;
	private String customerNo;
	private String customerRole;
	private String phone;
	private String source;
	private Date createTime;
	private Date updateTime;
	private String remark;
	
	public CustomerContactInfo() {
		super();
	}

	public CustomerContactInfo(String customerNo, String customerRole, String phone, String source, 
			Date createTime, Date updateTime, String remark) {
		super();
		this.customerNo = customerNo;
		this.customerRole = customerRole;
		this.phone = phone;
		this.source = source;
		this.createTime = createTime;
		this.updateTime = updateTime;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
				+ ", source=" + source + ", createTime=" + createTime + ", updateTime=" + updateTime + ", remark="
				+ remark + "]";
	}

}
