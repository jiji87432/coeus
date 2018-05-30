package com.pay.coeus.core.dao.coeus;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.CustomerDailyCount;
import com.pay.commons.utils.Page;

public interface CustomerDailyCountMapper {
	
	void insert(CustomerDailyCount customerDailyCount);
	
	CustomerDailyCount getByDate(@Param("dailyDate")String date);
	
	void update(CustomerDailyCount customerDailyCount);
	
	List<CustomerDailyCount> findAllList(@Param("page") Page<List<CustomerDailyCount>> page,@Param("param") Map<String,String> param);
	
}
