package com.pay.coeus.api.inner.dubbo;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.CustomerTransDay;
import com.pay.commons.utils.Page;

/**
 * 商户活跃订单查询
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerActiveOrderFacade 此处填写需要参考的类
 * @version 2017年11月21日 上午6:11:16 
 * @author yuze.luo
 */
public interface CustomerActiveOrderFacade {
	
	/**
	 * 获取一段时间内的活跃商户数、交易笔数、交易总额
	 * @Description  一句话描述方法用法
	 * @param strTime
	 * @param endTime
	 * @return  map:ACTIVENUM活跃商户数, TRANSAMOUNT交易总额, TRANSNUM交易笔数
	 * @see 需要参考的类或方法
	 */
	Map<String, String> getActiveCustomerData(String strTime, String endTime);
	
	/**
	 * 分页日交易明细查询
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	Page<List<CustomerTransDay>> findAllList(Page<List<CustomerTransDay>> page, Map<String, String> param);

	/**
	 * 分页日交易汇总查询
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	Page<List<Map<String, String>>> findAllCountList(Page<List<Map<String, String>>> page, Map<String, String> param);

	
}
