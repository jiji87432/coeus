package com.pay.coeus.core.dao.coeus;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PosMapper {

	void insertPosRequestRecord(@Param("param") Map<String,Object> param);
	void insertPosTransStatus(@Param("param") Map<String,Object> param);
}
