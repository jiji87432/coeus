package com.pay.coeus.core.dao.boss;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PosRequestMapper {
	
	/**
	 * 获取一段时间内有交互的支持sim卡监控商编
	 * @Description  一句话描述方法用法
	 * @param simSupplier 支持监控的供货商简码
	 * @param strDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<Map<String, String>> getActiveCustomer(@Param("simSupplier")String simSupplier, @Param("strTime")String strDate
			, @Param("endTime")String endDate);

	/**
	 * 获取商编交互数量
	 * @Description  一句话描述方法用法
	 * @param customerNo
	 * @param formatDate
	 * @return
	 * @see 需要参考的类或方法
	 */
	int getCountByCustomerNo(@Param("customerNo")String customerNo, @Param("strTime")String strDate);
	
	
}
