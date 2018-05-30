package com.pay.coeus.model.entity;

import java.util.Date;

/**
 * 新老商户数据绑定激活数据沉淀
 * @author yongda.ren
 *
 */
public class CustomerNewOldInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142970304721804276L;
	/**
	 * 商户编号
	 */
	private String customerNo;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 绑定时间
	 */
	private Date bindTime;
	/**
	 * 激活时间
	 */
	private Date activateTime;
	/**
	 * 最后更新时间
	 */
	private Date lastModifyTime;
	/**
	 * 是否新商户
	 */
	private String isNew;
	
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
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	@Override
	public String toString() {
		return "CustomerNewOldInfo [customerNo=" + customerNo + ", createTime="
				+ createTime + ", bindTime=" + bindTime + ", activateTime="
				+ activateTime + ", lastModifyTime=" + lastModifyTime
				+ ", isNew=" + isNew + "]";
	}
	
}
