package com.pay.coeus.core.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pay.coeus.model.entity.TestCoeus;
import com.pay.commons.utils.Page;

public interface TestService {

	public void testInsert();

	public List<Map<String, String>> findAllList(Page page);

	public TestCoeus findById(Long id);
}
