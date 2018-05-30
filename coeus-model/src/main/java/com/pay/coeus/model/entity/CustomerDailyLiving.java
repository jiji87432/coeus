package com.pay.coeus.model.entity;

import java.util.Date;

public class CustomerDailyLiving extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1566589262974213796L;
	private String customerNo;
	private int dailyCount;
	private String dailyDate;
	private String source;
	private Date createTime;
	private String remark;
	
	public CustomerDailyLiving() {
		super();
	}

	public CustomerDailyLiving(String customerNo, int dailyCount, String dailyDate, String source, Date createTime,
			String remark) {
		super();
		this.customerNo = customerNo;
		this.dailyCount = dailyCount;
		this.dailyDate = dailyDate;
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

	public int getDailyCount() {
		return dailyCount;
	}

	public void setDailyCount(int dailyCount) {
		this.dailyCount = dailyCount;
	}

	public String getDailyDate() {
		return dailyDate;
	}

	public void setDailyDate(String dailyDate) {
		this.dailyDate = dailyDate;
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
		return "CustomerDailyLiving [customerNo=" + customerNo + ", dailyCount=" + dailyCount + ", dailyDate="
				+ dailyDate + ", source=" + source + ", createTime=" + createTime + ", remark=" + remark + "]";
	}

}
