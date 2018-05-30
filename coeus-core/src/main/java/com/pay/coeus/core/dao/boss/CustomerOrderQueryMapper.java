package com.pay.coeus.core.dao.boss;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CustomerOrderQueryMapper {
	
	public List<Map<String,Object>> findTransTimeByTime(@Param("day") int day, @Param("customerNos") List<String> customerNos);
	
}
