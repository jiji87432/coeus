package com.pay.coeus.core.dao.boss;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.CustomerTransDay;
import com.pay.commons.utils.Page;

public interface CustomerTransDayMapper {
	
	/**
	 * 获取一段时间内的活跃商户数、交易笔数、交易总额
	 * @Description  一句话描述方法用法
	 * @param strDate
	 * @param endDate
	 * @return activeNum活跃商户数, transAmount交易总额, transNum交易笔数
	 * @see 需要参考的类或方法
	 */
	Map<String, String> getActiveCustomerData(@Param("strTime")String strDate, @Param("endTime")String endDate);

	/**
	 * 获取一段时间内新入网活跃商户数、交易笔数、交易总额
	 * @Description  一句话描述方法用法
	 * @param strDate
	 * @param endDate
	 * @return NEWACTIVENUM活跃商户数, NEWACTIVENUM交易总额, NEWACTIVEAMOUNT交易笔数
	 * @see 需要参考的类或方法
	 */
	Map<String, String> getNewCustomerData(@Param("strTime")String strDate, @Param("endTime")String endDate);
	
	/**
	 * 分页查询
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<CustomerTransDay> findAllList(@Param("page") Page<List<CustomerTransDay>> page,@Param("param") Map<String,String> param);

	/**
	 * 
	 * @Description  一句话描述方法用法
	 * @param page
	 * @param param
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<Map<String, String>> findAllCountList(@Param("page")Page<List<Map<String, String>>> page,@Param("param") Map<String, String> param);
	
	
	
}
