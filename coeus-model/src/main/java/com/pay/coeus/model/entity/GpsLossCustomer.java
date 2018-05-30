package com.pay.coeus.model.entity;

import java.util.Date;
/**
 * 流失商户信息表
 * @Description: 这里用一句话描述这个类的作用
 * @see: GpsLossCustomer 此处填写需要参考的类
 * @version 2017年10月25日 上午6:41:38 
 * @author yuze.luo
 */
public class GpsLossCustomer extends BaseEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3611170713059531938L;
	private String customerNo;
	private Date createTime;
	
	public GpsLossCustomer() {
		super();
	}
	
	public GpsLossCustomer(String customerNo, Date createTime) {
		super();
		this.customerNo = customerNo;
		this.createTime = createTime;
	}

	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "GpsLossCustomer [customerNo=" + customerNo + ", createTime=" + createTime + "]";
	}
}
