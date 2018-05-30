package com.pay.coeus.model.entity;

import java.util.Date;

/**
 * 监控SIM流量批次表
 * @Description: 这里用一句话描述这个类的作用
 * @see: GpsMonitorBatch 此处填写需要参考的类
 * @version 2017年10月25日 上午6:38:55 
 * @author yuze.luo
 */
public class GpsMonitorBatch extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5471223343275987949L;
	private String dateScope;
	private Date createTime;
	
	public GpsMonitorBatch() {
		super();
	}

	public GpsMonitorBatch(String dateScope, Date createTime) {
		super();
		this.dateScope = dateScope;
		this.createTime = createTime;
	}

	public String getDateScope() {
		return dateScope;
	}

	public void setDateScope(String dateScope) {
		this.dateScope = dateScope;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "GpsMonitorBatch [dateScope=" + dateScope + ", createTime=" + createTime + "]";
	}
}
