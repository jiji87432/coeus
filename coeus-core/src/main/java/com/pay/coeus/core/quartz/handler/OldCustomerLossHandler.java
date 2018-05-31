package com.pay.coeus.core.quartz.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.pay.coeus.common.utils.DateUtils;
import com.pay.coeus.common.utils.StringUtils;
import com.pay.coeus.core.service.OldCustomerLossService;
import com.pay.dsp.common.biz.model.ReturnT;
import com.pay.dsp.core.handler.BaseJobHandler;
import com.pay.dsp.core.handler.annotation.JobHander;

/**
 * 
 * <p>Title:OldCustomerLossJob</p>
 * <p>Description:流失预警定时</p>
 * @author yongda.ren
 * @date 2017年10月25日 上午11:35:46
 */
@JobHander(value = "oldCustomerLossHandler")
@PropertySource("classpath:/application.properties")
@Component
public class OldCustomerLossHandler extends BaseJobHandler {
	
	private Logger logger = LoggerFactory.getLogger(OldCustomerLossHandler.class);

	@Value("${com.pay.oldcustomerloss.day}")
	private String days;
	
	@Autowired
	private OldCustomerLossService oldCustomerLossService;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		int dayOfMonth = DateUtils.getDayOfMonth() -1;
		String[] split = StringUtils.split(days, ",");
		logger.info("dayOfMonth "+dayOfMonth);
		if(dayOfMonth == 0 ){
			int customerLoss = oldCustomerLossService.loadCustomerLoss();
			logger.info("loadCustomerLoss success row {}", customerLoss);
		}
		
		for (int i = 0; i < split.length; i++) {
			int day = Integer.valueOf(split[i]);
			if(day == dayOfMonth){
				try {
					//5号数据
					int customerLoss = 0;
					if(i == 0){
						logger.info("OldCustomerLoss in min i {}", split[i]);
						customerLoss = oldCustomerLossService.loadSuspectCustomerLoss((30-day), 32);
					}else {
						int stap = Integer.valueOf(split[i]) - Integer.valueOf(split[i-1]); 
						customerLoss = oldCustomerLossService.loadSuspectCustomerLoss((30-day), (30-day+stap));
						logger.info("OldCustomerLoss in stap,day {},{}", stap,day);
					}
					logger.info("OldCustomerLoss success row {}",customerLoss);
				} catch (Exception e) {
					logger.error("OldCustomerLoss e", e);
				}
				break;
			}
		}
		return ReturnT.SUCCESS;
	}
}
