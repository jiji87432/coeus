package com.pay.coeus.core.dubbo.inner;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.api.inner.dubbo.TestDubboServiceFacade;
import com.pay.coeus.common.utils.RedisUtil;
import com.pay.coeus.core.service.TestService;
import com.pay.coeus.model.entity.TestCoeus;
import com.pay.commons.utils.Page;
@Service("testDubboServiceFacade")
public class TestDubboServiceFacadeImpl implements TestDubboServiceFacade {

	Logger logger = LoggerFactory.getLogger(TestDubboServiceFacadeImpl.class);
	
	@Autowired
	private TestService testService;
	
	@Override
	public String testDubbo() {
		RedisUtil.setKeyValue("aaaa", "bbbb", 60*5);
		String value = RedisUtil.getValue("aaaa");
		logger.info("value {}", value);
		System.out.println(value);
		//testService.testInsert();
		return "hello";
	}

	@Override
	public Page<List<Map<String, String>>> findAllList(Page page) {
		List<Map<String,String>> list = testService.findAllList(page);
		page.setObject(list);
		return page;
	}

	@Override
	public TestCoeus findById(Long id) {
		return testService.findById(id);
	}

}
