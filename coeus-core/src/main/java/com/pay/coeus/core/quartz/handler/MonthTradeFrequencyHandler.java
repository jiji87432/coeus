package com.pay.coeus.core.quartz.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pay.coeus.common.utils.DateUtils;
import com.pay.coeus.core.service.MonthTradeFrequencyService;
import com.pay.dsp.common.biz.model.ReturnT;
import com.pay.dsp.core.handler.BaseJobHandler;
import com.pay.dsp.core.handler.annotation.JobHander;

/**
 * <p>Title:MonthTradeFrequencyJob</p>
 * <p>Description:每月交易频次沉淀</p>
 * @author yongda.ren
 * @date 2017年10月24日 下午5:40:04
 */
@JobHander(value = "monthTradeFrequencyHandler")
@Component
public class MonthTradeFrequencyHandler extends BaseJobHandler {
	
	private Logger logger = LoggerFactory.getLogger(MonthTradeFrequencyHandler.class);

	@Autowired
	private MonthTradeFrequencyService monthTradeFrequencyService;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
//		int dayOfMonth = DateUtils.getDayOfMonth();
		//if(dayOfMonth == 1){
			try {
				logger.info("MonthTradeFrequencyJob in");
				int frequency = monthTradeFrequencyService.insertMonthTradeFrequency();
				logger.info("MonthTradeFrequencyJob success row {}", frequency);
			} catch (Exception e) {
				logger.error("MonthTradeFrequencyJob error ", e);
			}
		//}
		return ReturnT.SUCCESS;
	}

}
