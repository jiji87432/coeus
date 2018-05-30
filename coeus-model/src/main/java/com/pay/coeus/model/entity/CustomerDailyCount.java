package com.pay.coeus.model.entity;

import java.util.Date;

/**
 * 各平台每日活跃统计
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerDailyCount 此处填写需要参考的类
 * @version 2017年11月9日 上午9:49:09 
 * @author yuze.luo
 */
public class CustomerDailyCount extends BaseEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8898266768015288153L;
	private Integer app;
	private Integer weixin;
	private Integer weixinAll;
	private Integer weixinPos;
	private Integer merchant;
	private String dailyDate;
	private Date createTime;
	private String remark;
	
	public CustomerDailyCount() {
		super();
	}
	
	public CustomerDailyCount(Integer app, Integer weixin, Integer weixinAll, Integer weixinPos, Integer merchant,
			String dailyDate, Date createTime, String remark) {
		super();
		this.app = app;
		this.weixin = weixin;
		this.weixinAll = weixinAll;
		this.weixinPos = weixinPos;
		this.merchant = merchant;
		this.dailyDate = dailyDate;
		this.createTime = createTime;
		this.remark = remark;
	}

	public Integer getWeixinAll() {
		return weixinAll;
	}

	public void setWeixinAll(Integer weixinAll) {
		this.weixinAll = weixinAll;
	}

	public Integer getWeixinPos() {
		return weixinPos;
	}

	public void setWeixinPos(Integer weixinPos) {
		this.weixinPos = weixinPos;
	}

	public Integer getApp() {
		return app;
	}

	public void setApp(Integer app) {
		this.app = app;
	}

	public Integer getWeixin() {
		return weixin;
	}

	public void setWeixin(Integer weixin) {
		this.weixin = weixin;
	}

	public Integer getMerchant() {
		return merchant;
	}

	public void setMerchant(Integer merchant) {
		this.merchant = merchant;
	}

	public String getDailyDate() {
		return dailyDate;
	}

	public void setDailyDate(String dailyDate) {
		this.dailyDate = dailyDate;
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
		return "CustomerDailyCount [app=" + app + ", weixin=" + weixin + ", weixinAll=" + weixinAll + ", weixinPos="
				+ weixinPos + ", merchant=" + merchant + ", dailyDate=" + dailyDate + ", createTime=" + createTime
				+ ", remark=" + remark + "]";
	}

	
}
