package com.pay.coeus.model.entity;

import java.util.Date;

/**
 * 监控商户信息表
 * @Description: 这里用一句话描述这个类的作用
 * @see: GpsMonitorCustomer 此处填写需要参考的类
 * @version 2017年10月25日 上午6:42:53 
 * @author yuze.luo
 */
public class GpsMonitorCustomer extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5477336643275371949L;
	private String customerNo;
	private String iccid;
	private String simSupplier;
	private Date createTime;
	
	public GpsMonitorCustomer() {
		super();
	}
	
	public GpsMonitorCustomer(String customerNo, String iccid, String simSupplier, Date createTime) {
		super();
		this.customerNo = customerNo;
		this.iccid = iccid;
		this.simSupplier = simSupplier;
		this.createTime = createTime;
	}

	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getSimSupplier() {
		return simSupplier;
	}
	public void setSimSupplier(String simSupplier) {
		this.simSupplier = simSupplier;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "MonitorCustomer [customerNo=" + customerNo + ", iccid=" + iccid + ", simSupplier=" + simSupplier
				+ ", createTime=" + createTime + "]";
	}
	
}
