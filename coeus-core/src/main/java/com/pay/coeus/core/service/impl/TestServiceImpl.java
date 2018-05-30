package com.pay.coeus.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.core.dao.coeus.TestMapper;
import com.pay.coeus.core.service.TestService;
import com.pay.coeus.model.entity.TestCoeus;
import com.pay.commons.utils.Page;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestMapper testMapper;
	
	@Override
	public void testInsert(){
		
		//testMapper.insert("aa", "bb");
		
	}

	@Override
	public List<Map<String, String>> findAllList(Page page) {
		return testMapper.findAllList(page);
	}

	@Override
	public TestCoeus findById(Long id) {
		return testMapper.findById(id);
	}

}
