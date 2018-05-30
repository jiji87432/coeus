package com.pay.coeus.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.boss.MonthTradeFrequencyMapper;
import com.pay.coeus.core.service.MonthTradeFrequencyService;

@Service
public class MonthTradeFrequencyServiceImpl implements MonthTradeFrequencyService {

	@Autowired
	private MonthTradeFrequencyMapper monthTradeFrequencyMapper;
	
	@Override
	public int insertMonthTradeFrequency(){
		return monthTradeFrequencyMapper.insertMonthTradeFrequency();
	}
	
}
