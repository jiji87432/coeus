package com.pay.coeus.model.entity;

import java.util.Date;

public class SimcardDeductFeeDetail extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String customerNo; // 商户编号
	private String imsi; // Sim卡IMSI串号
	private String posSn; // pos序列号
	private String posCati; // pos终端号
	private Date posFirstBindTime; // 机具第一次绑定时间
	private Date firstDeductFeeTime; // 第一次扣款时间
	private Date deductFeeSuccTime; // 扣款完成时间
	private Date nextMessageTime; // 下次通知时间
	private Double totalFee; // 应扣金额
	private Double deductFee; // 实扣金额
	private String deductStatus; // 扣款状态PREFREEZE、SUCCESS
	private String messageStatus; // 通知状态Y,是 N否
	private String simStatus; // SIM卡状态TRUE可用，FALSE禁用
	private Date messageTime; // 信息发送时间
	private Date simCloseTime; // SIM卡关闭时间
	private Date createTime; // 创建时间
	private String freezeNo; // 预冻结编号
	private String requestId; // 交易请求ID
	private Date refundTime;//退费时间 
	private String refundOperator;//退费操作员
	private String transOrder;//流水id
	private String refundTransOrder;// 退费流水ID
	public SimcardDeductFeeDetail() {
		super();
	}
	public SimcardDeductFeeDetail(String customerNo, String imsi, String posSn, String posCati, Date posFirstBindTime,
			Date firstDeductFeeTime, Date deductFeeSuccTime, Date nextMessageTime, Double totalFee, Double deductFee,
			String deductStatus, String messageStatus, String simStatus, Date messageTime, Date simCloseTime,
			Date createTime, String freezeNo, String requestId, Date refundTime, String refundOperator,
			String transOrder, String refundTransOrder) {
		super();
		this.customerNo = customerNo;
		this.imsi = imsi;
		this.posSn = posSn;
		this.posCati = posCati;
		this.posFirstBindTime = posFirstBindTime;
		this.firstDeductFeeTime = firstDeductFeeTime;
		this.deductFeeSuccTime = deductFeeSuccTime;
		this.nextMessageTime = nextMessageTime;
		this.totalFee = totalFee;
		this.deductFee = deductFee;
		this.deductStatus = deductStatus;
		this.messageStatus = messageStatus;
		this.simStatus = simStatus;
		this.messageTime = messageTime;
		this.simCloseTime = simCloseTime;
		this.createTime = createTime;
		this.freezeNo = freezeNo;
		this.requestId = requestId;
		this.refundTime = refundTime;
		this.refundOperator = refundOperator;
		this.transOrder = transOrder;
		this.refundTransOrder = refundTransOrder;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getPosSn() {
		return posSn;
	}
	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}
	public String getPosCati() {
		return posCati;
	}
	public void setPosCati(String posCati) {
		this.posCati = posCati;
	}
	public Date getPosFirstBindTime() {
		return posFirstBindTime;
	}
	public void setPosFirstBindTime(Date posFirstBindTime) {
		this.posFirstBindTime = posFirstBindTime;
	}
	public Date getFirstDeductFeeTime() {
		return firstDeductFeeTime;
	}
	public void setFirstDeductFeeTime(Date firstDeductFeeTime) {
		this.firstDeductFeeTime = firstDeductFeeTime;
	}
	public Date getDeductFeeSuccTime() {
		return deductFeeSuccTime;
	}
	public void setDeductFeeSuccTime(Date deductFeeSuccTime) {
		this.deductFeeSuccTime = deductFeeSuccTime;
	}
	public Date getNextMessageTime() {
		return nextMessageTime;
	}
	public void setNextMessageTime(Date nextMessageTime) {
		this.nextMessageTime = nextMessageTime;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	public Double getDeductFee() {
		return deductFee;
	}
	public void setDeductFee(Double deductFee) {
		this.deductFee = deductFee;
	}
	public String getDeductStatus() {
		return deductStatus;
	}
	public void setDeductStatus(String deductStatus) {
		this.deductStatus = deductStatus;
	}
	public String getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}
	public String getSimStatus() {
		return simStatus;
	}
	public void setSimStatus(String simStatus) {
		this.simStatus = simStatus;
	}
	public Date getMessageTime() {
		return messageTime;
	}
	public void setMessageTime(Date messageTime) {
		this.messageTime = messageTime;
	}
	public Date getSimCloseTime() {
		return simCloseTime;
	}
	public void setSimCloseTime(Date simCloseTime) {
		this.simCloseTime = simCloseTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFreezeNo() {
		return freezeNo;
	}
	public void setFreezeNo(String freezeNo) {
		this.freezeNo = freezeNo;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Date getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	public String getRefundOperator() {
		return refundOperator;
	}
	public void setRefundOperator(String refundOperator) {
		this.refundOperator = refundOperator;
	}
	public String getTransOrder() {
		return transOrder;
	}
	public void setTransOrder(String transOrder) {
		this.transOrder = transOrder;
	}
	public String getRefundTransOrder() {
		return refundTransOrder;
	}
	public void setRefundTransOrder(String refundTransOrder) {
		this.refundTransOrder = refundTransOrder;
	}
	
}
