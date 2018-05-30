package com.pay.coeus.core.dao.coeus;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.GpsMonitorCustomer;

public interface GpsMonitorCustomerMapper {
	
	void insert(GpsMonitorCustomer gpsMonitorCustomer);
	
	GpsMonitorCustomer getByCustomerNo(@Param("customerNo")String customerNo);
	
	void delete(@Param("id")Long id);
	
	/**
	 * 批量插入
	 * @Description  一句话描述方法用法
	 * @param customerList
	 * @see 需要参考的类或方法
	 */
	void insertBatch(@Param("customerList") List<Map<String, String>> customerList);
	
	/**
	 * 根据供货商简码获取去重后的iccids
	 * @Description  一句话描述方法用法
	 * @param simSupplier
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<String> getBySimSupplier(@Param("simSupplier")String simSupplier);
	
	/**
	 * 获取总数量
	 * @Description  一句话描述方法用法
	 * @return
	 * @see 需要参考的类或方法
	 */
	int getTotalCount();

	/**
	 * 分页获取数据
	 * @Description  一句话描述方法用法
	 * @param offset
	 * @param onepagecount
	 * @return
	 * @see 需要参考的类或方法
	 */
	List<GpsMonitorCustomer> getByPage(@Param("startNum")int offset, @Param("onePageCount")int onePageCount);
}
