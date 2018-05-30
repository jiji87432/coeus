package com.pay.coeus.core.dao.coeus;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.GpsLossCustomer;

public interface GpsLossCustomerMapper {
	
	void insert(GpsLossCustomer gpsMonitorCustomer);
	
	void insertCustomer(@Param("customerNo")String customerNo);
	
	GpsLossCustomer getByCustomerNo(@Param("customerNo")String customerNo);
	
	void insertBatch(@Param("customerList") List<Map<String, String>> customerList);
	
	List<GpsLossCustomer> findLossCustomer(@Param("param")Map<String,String> param);
}
