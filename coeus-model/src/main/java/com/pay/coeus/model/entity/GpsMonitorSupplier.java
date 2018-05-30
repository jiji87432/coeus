package com.pay.coeus.model.entity;

import java.util.Date;

/**
 * 监控SIM供货商信息表
 * @Description: 这里用一句话描述这个类的作用
 * @see: GpsMonitorBatch 此处填写需要参考的类
 * @version 2017年11月14日 上午6:38:55 
 * @author yuze.luo
 */
public class GpsMonitorSupplier extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2676700250964014935L;
	private String name;
	private String supplier;
	private Integer status;
	private Date createTime;
	
	public GpsMonitorSupplier() {
		super();
	}

	public GpsMonitorSupplier(String name, String supplier, Integer status, Date createTime) {
		super();
		this.name = name;
		this.supplier = supplier;
		this.status = status;
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "GpsMonitorSupplier [name=" + name + ", supplier=" + supplier + ", status=" + status + ", createTime="
				+ createTime + "]";
	}
}
