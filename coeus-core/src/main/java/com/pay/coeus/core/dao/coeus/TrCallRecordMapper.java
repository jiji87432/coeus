package com.pay.coeus.core.dao.coeus;

import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface TrCallRecordMapper {

	void insertCallRecord(@Param("param") Map<String,Object> param);
	void insertCallRecordDetail(@Param("param") Map<String,Object> param);
}
