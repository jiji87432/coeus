package com.pay.coeus.core.quartz.handler;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pay.coeus.common.utils.DateUtils;
import com.pay.coeus.core.service.GpsMonitorCustomerService;
import com.pay.dsp.common.biz.model.ReturnT;
import com.pay.dsp.core.handler.BaseJobHandler;
import com.pay.dsp.core.handler.annotation.JobHander;

/**
 * 流量获取沉淀
 * @Description: 这里用一句话描述这个类的作用
 * @see: AppPushIdSyncJob 此处填写需要参考的类
 * @version 2017年10月23日 上午7:37:52 
 * @author yuze.luo
 */
@JobHander(value = "gpsDataAcquisitionHandler")
@Component
public class GpsDataAcquisitionHandler extends BaseJobHandler {
	
	private Logger logger = LoggerFactory.getLogger(GpsDataAcquisitionHandler.class);
	@Resource
	private GpsMonitorCustomerService gpsMonitorCustomerService;

	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		logger.info("GpsDataAcquisitionJob_start,time:{}", DateUtils.getDateTime());
		try {
			gpsMonitorCustomerService.gpsDataAcquisition();
		} catch (Exception e) {
			logger.error("GpsDataAcquisitionJob_失败", e);
		}
		logger.info("GpsDataAcquisitionJob_end,time:{}", DateUtils.getDateTime());
		return ReturnT.SUCCESS;
	}
	
}
