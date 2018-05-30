package com.pay.coeus.model.entity;

import java.util.Date;

public class Customer extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5477337743205375945L;
	private String customerNo;
	private String customerName;
	private String status;
	private String mcc;
	private Long agentId;
	private Date createTime;
	private Date openTime;
	
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	@Override
	public String toString() {
		return "Customer [customerNo=" + customerNo + ", customerName="
				+ customerName + ", status=" + status + ", mcc=" + mcc
				+ ", agentId=" + agentId + ", createTime=" + createTime
				+ ", openTime=" + openTime + "]";
	}
	
	

}
