package com.pay.coeus.core.service;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.CustomerTransDay;
import com.pay.commons.utils.Page;

/**
 * 日交易汇总查询服务
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerTransDayService 此处填写需要参考的类
 * @version 2017年11月21日 上午6:40:02 
 * @author yuze.luo
 */
public interface CustomerTransDayService{
	
	/**
	 * 获取一段时间内的活跃商户数、交易笔数、交易总额
	 * @Description  一句话描述方法用法
	 * @param strTime
	 * @param endTime
	 * @return map:ACTIVENUM活跃商户数, TRANSAMOUNT交易总额, TRANSNUM交易笔数
	 * @see 需要参考的类或方法
	 */
	public Map<String, String> getActiveCustomerData(String strTime, String endTime);
	
	/**
	 * 获取一段时间内的新入网商户数、交易笔数、交易总额
	 * @Description  一句话描述方法用法
	 * @param strTime
	 * @param endTime
	 * @return map:NEWNUM商户数,NEWACTIVENUM活跃商户数, NEWACTIVENUM交易总额, NEWACTIVEAMOUNT交易笔数
	 * @see 需要参考的类或方法
	 */
	public Map<String, String> getNewCustomerData(String strTime, String endTime);

	/**
	 * 分页日交易明细查询
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<CustomerTransDay> findAllList(Page<List<CustomerTransDay>> page, Map<String, String> param);

	/**
	 * 分页日交易汇总
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	public List<Map<String, String>> findAllCountList(Page<List<Map<String, String>>> page, Map<String, String> param);
}
