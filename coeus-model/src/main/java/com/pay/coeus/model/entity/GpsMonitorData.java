package com.pay.coeus.model.entity;

import java.util.Date;

/**
 * 监控SIM流量详情表
 */
public class GpsMonitorData extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6422701996449859292L;
	private Long batchId;
	private String iccid;
	private Double useFlow;
	private Date createTime;
	
	public GpsMonitorData() {
		super();
	}

	public GpsMonitorData(Long batchId, String iccid, Double useFlow, Date createTime) {
		super();
		this.batchId = batchId;
		this.iccid = iccid;
		this.useFlow = useFlow;
		this.createTime = createTime;
	}

	public Long getBatchId() {
		return batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public Double getUseFlow() {
		return useFlow;
	}

	public void setUseFlow(Double useFlow) {
		this.useFlow = useFlow;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "GpsMonitorData [batchId=" + batchId + ", iccid=" + iccid + ", useFlow=" + useFlow + ", createTime="
				+ createTime + "]";
	}
	
}
