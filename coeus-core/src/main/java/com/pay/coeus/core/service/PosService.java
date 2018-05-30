package com.pay.coeus.core.service;

import java.util.List;
import java.util.Map;

public interface PosService {

	void insertPosRecord(Map<String,Object> params,List<Map<String,Object>> listParams);
	void insertPosRequestRecord(Map<String,Object> param);
	void insertPosTransStatus(Map<String,Object> param);
}
