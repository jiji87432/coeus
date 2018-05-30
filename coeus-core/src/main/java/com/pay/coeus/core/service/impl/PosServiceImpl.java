package com.pay.coeus.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.coeus.PosMapper;
import com.pay.coeus.core.service.PosService;

@Service
public class PosServiceImpl implements PosService {

	@Resource
	private PosMapper posMapper;

	@Override
	public void insertPosRequestRecord(Map<String, Object> param) {
		posMapper.insertPosRequestRecord(param);
	}

	@Override
	public void insertPosTransStatus(Map<String, Object> param) {
		posMapper.insertPosTransStatus(param);
	}

	@Override
	public void insertPosRecord(Map<String, Object> param,
			List<Map<String, Object>> listParam) {
		insertPosRequestRecord(param);
		if(listParam!= null && listParam.size()>0){
			for(Map<String, Object> m :listParam){
				insertPosTransStatus(m);
			}
		}
		
	}
	
}
