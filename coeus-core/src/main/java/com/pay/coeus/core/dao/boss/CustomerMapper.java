package com.pay.coeus.core.dao.boss;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {
	
	@Select("select c.customer_no from customer c where 1=1 and c.OPEN_TIME > TRUNC(SYSDATE) -1 "
			+ "and c.OPEN_TIME < TRUNC(SYSDATE) and c.AGENT_ID <> 37447301 "
			+ "and c.MCC not in ('9990','9991','9992','9993','9997','9998','9999') ")
	public List<String> findCustomerNewOpen();

	
	@Select("select count(c.id) from customer c where c.OPEN_TIME >= to_date(#{strTime},'yyyy-MM-dd hh24:mi:ss')"
			+ "and c.OPEN_TIME <= to_date(#{endTime},'yyyy-MM-dd hh24:mi:ss') ")
	public int getByOpenTime(@Param("strTime") String strTime, @Param("endTime")String endTime);
}
