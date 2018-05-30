package com.pay.coeus.core.dao.coeus;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.pay.coeus.model.entity.CustomerContactHistory;
import com.pay.commons.utils.Page;

public interface CustomerContactHistoryMapper {
	
	void insert(CustomerContactHistory customerContactHistory);

	void insertBatch(@Param("infoList")List<CustomerContactHistory> list);

	List<CustomerContactHistory> findAllList(@Param("page")Page<List<CustomerContactHistory>> page, @Param("param")Map<String, String> param);

	List<CustomerContactHistory> getPhonesByCustomerNo(@Param("customerNo")String customerNo);

	
}
