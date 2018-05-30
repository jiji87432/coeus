package com.pay.coeus.core.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.coeus.TrCallRecordMapper;
import com.pay.coeus.core.service.TrCallRecordService;

@Service
public class TrCallRecordServiceImpl implements TrCallRecordService {

	@Resource
	private TrCallRecordMapper trCallRecordMapper;
	@Override
	public void insertCallRecord(Map<String, Object> param) {
		trCallRecordMapper.insertCallRecord(param);

	}
	@Override
	public void insertCallRecordDetail(Map<String, Object> param) {
		trCallRecordMapper.insertCallRecordDetail(param);
	}

}
