package com.pay.coeus.core.dubbo.inner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.api.inner.dubbo.CommonScriptServiceFacade;
import com.pay.coeus.core.service.CommonScriptService;
@Service("commonScriptServiceFacade")
public class CommonScriptServiceFacadeImpl implements CommonScriptServiceFacade {

	Logger logger = LoggerFactory.getLogger(CommonScriptServiceFacadeImpl.class);
	
	@Autowired
	private CommonScriptService commonScriptService;

	@Override
	public void updateScript(String sql) {
		commonScriptService.updateScript(sql);
	}
	
}
