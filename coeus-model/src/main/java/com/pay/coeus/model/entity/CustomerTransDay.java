package com.pay.coeus.model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户日交易统计
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerDailyCount 此处填写需要参考的类
 * @version 2017年11月23日 上午9:49:09 
 * @author yuze.luo
 */
public class CustomerTransDay extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2533667268526594419L;
	private String customerNo;
	private String customerName;
	private String agentNo;
	private String agentName;
	private BigDecimal transAmount;
	private Integer transNum;
	private Date orderTime;
	private Date createTime;
	
	public CustomerTransDay() {
		super();
	}

	public CustomerTransDay(String customerNo, String customerName, String agentNo, String agentName,
			BigDecimal transAmount, Integer transNum, Date orderTime, Date createTime) {
		super();
		this.customerNo = customerNo;
		this.customerName = customerName;
		this.agentNo = agentNo;
		this.agentName = agentName;
		this.transAmount = transAmount;
		this.transNum = transNum;
		this.orderTime = orderTime;
		this.createTime = createTime;
	}

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

	public String getAgentNo() {
		return agentNo;
	}

	public void setAgentNo(String agentNo) {
		this.agentNo = agentNo;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public BigDecimal getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public Integer getTransNum() {
		return transNum;
	}

	public void setTransNum(Integer transNum) {
		this.transNum = transNum;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CustomerTransDay [customerNo=" + customerNo + ", customerName=" + customerName + ", agentNo=" + agentNo
				+ ", agentName=" + agentName + ", transAmount=" + transAmount + ", transNum=" + transNum
				+ ", orderTime=" + orderTime + ", createTime=" + createTime + "]";
	}
}
