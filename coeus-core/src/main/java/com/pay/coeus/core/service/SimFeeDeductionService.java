package com.pay.coeus.core.service;

public interface SimFeeDeductionService {

	/**
	 * 创建SIM卡预支付订单
	 * @Description  一句话描述方法用法
	 * @see 需要参考的类或方法
	 */
	public void createFreezeSimDeduct();
	
	/**
	 * 交互失败的再次调用一下
	 * @Description  一句话描述方法用法
	 * @see 需要参考的类或方法
	 */
	public void modifyWaitDeductToPreereeze();

	/**
	 * 判断修改预支付订单状态
	 * @Description  一句话描述方法用法
	 * @see 需要参考的类或方法
	 */
	public void updateSimDeductByQueryFareStatus();

}
