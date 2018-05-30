package com.pay.coeus.api.inner.dubbo;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.TestCoeus;
import com.pay.commons.utils.Page;

public interface TestDubboServiceFacade {
	
	String testDubbo();

	Page<List<Map<String, String>>> findAllList(Page page);
	
	TestCoeus findById(Long id);

}
