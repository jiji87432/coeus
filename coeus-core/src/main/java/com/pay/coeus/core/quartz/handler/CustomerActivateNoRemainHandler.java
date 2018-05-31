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
 * <p>Title:CustomerActivateNoRemainJob</p>
 * <p>Description:新商户激活未留存</p>
 * @author yongda.ren
 * @date 2017年10月24日 下午5:41:09
 */
@JobHander(value = "customerActivateNoRemainHandler")
@PropertySource("classpath:/application.properties")
@Component
public class CustomerActivateNoRemainHandler extends BaseJobHandler {
	
	Logger logger = LoggerFactory.getLogger(CustomerActivateNoRemainHandler.class);

	@Autowired
	private NewCustomerActivateService newCustomerActivateService;
	
	@Value("${com.pay.newcustomeractivate.day}")
	private String dayStr;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		logger.info("=="+dayStr);
		Integer day = Integer.valueOf(dayStr);
		try {
			//新商户激活未留存
			int loadNoRemain = newCustomerActivateService.loadNewCustomerActivateNoRemain(day);
			logger.info("loadNewCustomerActivateNoRemain success {}", loadNoRemain);
		} catch (Exception e) {
			logger.error("loadNewCustomerActivateNoRemain error", e);
		}
		return ReturnT.SUCCESS;
	}

}
