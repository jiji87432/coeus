package com.pay.coeus.core.dubbo.inner;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.api.inner.dubbo.ActivateRetainedServiceFacade;
import com.pay.coeus.core.service.ActivateRetainedService;

@Service("activateRetainedServiceFacade")
public class ActivateRetainedServiceFacadeImpl implements ActivateRetainedServiceFacade {
	Logger logger = LoggerFactory.getLogger(ActivateRetainedServiceFacadeImpl.class);
	@Autowired
	private ActivateRetainedService activateRetainedService;
	
	@Override
	public Map<String,Object> loadActivateRetainedData(Integer month){
		logger.info("iiiiiiiiii");
		return activateRetainedService.loadActivateRetainedData(month);
	}

}
