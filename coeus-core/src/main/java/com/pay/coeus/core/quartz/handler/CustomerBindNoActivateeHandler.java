package com.pay.coeus.core.quartz.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.pay.coeus.core.service.NewCustomerActivateService;
import com.pay.dsp.common.biz.model.ReturnT;
import com.pay.dsp.core.handler.BaseJobHandler;
import com.pay.dsp.core.handler.annotation.JobHander;

/**
 * 
 * <p>Title:NewCustomerBindNoActivateeJob</p>
 * <p>Description:绑定激活时间统计</p>
 * @author yongda.ren
 * @date 2017年10月24日 下午5:41:09
 */
@JobHander(value = "customerBindNoActivateeHandler")
@Component
@PropertySource("classpath:/constant.properties")
public class CustomerBindNoActivateeHandler extends BaseJobHandler {
	
	Logger logger = LoggerFactory.getLogger(CustomerBindNoActivateeHandler.class);

	@Autowired
	private NewCustomerActivateService newCustomerActivateService;
	
	@Value("${com.pay.newcustomeractivate.day}")
	private String dayStr;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		logger.info("=="+dayStr);
		Integer day = Integer.valueOf(dayStr);
		try {
			//提取绑定未激活数据
			int loadNoActivate = newCustomerActivateService.loadNewCustomerBindNoActivate(day);
			logger.info("loadNewCustomerBindNoActivate success {}", loadNoActivate);
		} catch (Exception e) {
			logger.error("loadNewCustomerBindNoActivate error", e);
		}
		return ReturnT.SUCCESS;
	}

}
