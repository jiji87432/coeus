package com.pay.coeus.portal.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pay.coeus.api.inner.bean.ReturnUtil;
import com.pay.coeus.api.inner.dubbo.ActivateRetainedServiceFacade;

@Controller
@RequestMapping("/dataQuery")
public class ActivateRetainedController {
	private static final Logger logger = LoggerFactory.getLogger(ActivateRetainedController.class);
	@Autowired
	private ActivateRetainedServiceFacade activateRetainedServiceFacade;

	@RequestMapping("/toActivateRetained.action")
	public ModelAndView toActivateRetained(){
		logger.info("------------------");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("activiteRetainedData/activiteRetainedData");
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/activateRetaine.action")
	public String loadActivateRetained(@RequestParam("month") Integer month){
		try {
			logger.info("in ------------------");
			Map<String, Object> loadActivateRetainedData = activateRetainedServiceFacade.loadActivateRetainedData(month);
			return JSON.toJSONString(ReturnUtil.success(loadActivateRetainedData));
		} catch (Exception e) {
			logger.error("loadActivateRetained",e);
			return JSON.toJSONString(ReturnUtil.error(e));
		}
	}
}
