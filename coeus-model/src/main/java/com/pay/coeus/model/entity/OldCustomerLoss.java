package com.pay.coeus.model.entity;

import java.util.Date;

/**
 * 
 * <p>Title:OldCustomerLoss</p>
 * <p>Description:老商户流失</p>
 * @author yongda.ren
 * @date 2017年10月25日 下午2:11:48
 */
public class OldCustomerLoss extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7702973154271226350L;

	/**
	 * 商户编号
	 */
	private String customerNo;
	/**
	 * 商户名称
	 */
	private String customerName;
	/**
	 * 商户状态
	 */
	private String customerStatus;
	/**
	 * 商户等级
	 */
	private Integer customerLevel;
	/**
	 * 商户真实性
	 */
	private String customerCheckInfo;
	/**
	 * 绑定时间
	 */
	private Date bindTime;
	/**
	 * 激活时间
	 */
	private Date activateTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 结果类型
	 */
	private String resultType;
	/**
	 * 联系人
	 */
	private String linkMan;
	
	/**
	 * 流量卡ID
	 */
	private String isLoss;
	
	/**
	 * 是否有交易
	 */
	private String isTrans;
	
	/**
	 * 交易时间
	 */
	private Date transTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * pos数量
	 */
	private Integer posCount;
	
	public String getIsLoss() {
		return isLoss;
	}
	public void setIsLoss(String isLoss) {
		this.isLoss = isLoss;
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
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public Integer getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(Integer customerLevel) {
		this.customerLevel = customerLevel;
	}
	public String getCustomerCheckInfo() {
		return customerCheckInfo;
	}
	public void setCustomerCheckInfo(String customerCheckInfo) {
		this.customerCheckInfo = customerCheckInfo;
	}
	public Date getBindTime() {
		return bindTime;
	}
	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}
	public Date getActivateTime() {
		return activateTime;
	}
	public void setActivateTime(Date activateTime) {
		this.activateTime = activateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getIsTrans() {
		return isTrans;
	}
	public void setIsTrans(String isTrans) {
		this.isTrans = isTrans;
	}
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getPosCount() {
		return posCount;
	}
	public void setPosCount(Integer posCount) {
		this.posCount = posCount;
	}
	@Override
	public String toString() {
		return "OldCustomerLoss [customerNo=" + customerNo + ", customerName="
				+ customerName + ", customerStatus=" + customerStatus
				+ ", customerLevel=" + customerLevel + ", customerCheckInfo="
				+ customerCheckInfo + ", bindTime=" + bindTime
				+ ", activateTime=" + activateTime + ", createTime="
				+ createTime + ", resultType=" + resultType + ", linkMan="
				+ linkMan + ", isLoss=" + isLoss + ", isTrans=" + isTrans
				+ ", transTime=" + transTime + ", updateTime=" + updateTime
				+ ", posCount=" + posCount + "]";
	}
	
}
