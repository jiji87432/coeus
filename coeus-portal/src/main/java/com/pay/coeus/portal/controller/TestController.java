package com.pay.coeus.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.pay.coeus.api.inner.dubbo.TestDubboServiceFacade;
import com.pay.coeus.model.entity.NewCustomerActivate;
import com.pay.coeus.model.entity.TestCoeus;
import com.pay.commons.utils.Page;
import com.pay.commons.web.WebConstants;

@Controller
@RequestMapping("/test")
public class TestController {

	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestDubboServiceFacade testDubboServiceFacade;
	
	@RequestMapping("/te.action")
	public ModelAndView testCon(){
		//String testDubbo = testDubboServiceFacade.testDubbo();
		//logger.info(testDubbo);
		//System.out.println(testDubbo);
		ModelAndView model = new ModelAndView();
		model.setViewName("list/list");
		model.addObject("hello", "你好");
		return model;
	}
	
	@RequestMapping(value="/findList2.action")
	@ResponseBody
	public String findAllListAll(HttpServletRequest request,@RequestParam Map<String,Object> param){
		String authName = (String)request.getSession().getAttribute(WebConstants.SESSION_OPERATOR);
		String operator = (String)request.getSession().getAttribute(WebConstants.SESSION_OPERATOR);
		Object attribute = request.getSession().getAttribute(WebConstants.BOSS_OPERATOR_RESOURCE);
		Page<List<Map<String,String>>> page = null;//new Page<>();
		/*if(param.get("currentPage")==null || "".equals(param.get("currentPage"))){
			logger.info("==============");
			//page.setCurrentPage(1);
			//page.setShowCount(100000);
		}else{
			int showCount = Integer.parseInt(param.get("showCount"));
			Integer currentPage = Integer.parseInt(param.get("currentPage"));
			page = new Page<>();
			page.setCurrentPage(currentPage);
			page.setShowCount(showCount);
		}*/
		Object object = request.getAttribute("page");
		page = (Page) object;
		logger.info(page.toString());
		logger.info(authName);
		Page<List<Map<String,String>>> list = testDubboServiceFacade.findAllList(page);
		String jsonString = JSON.toJSONString(ReturnUtil.success(list));
		logger.info(jsonString);
		
		return jsonString;
	}
	
	@RequestMapping("queryDetail")
	public ModelAndView queryDetail(Long id){
		TestCoeus testCoeus = testDubboServiceFacade.findById(id);
		ModelAndView model = new ModelAndView();
		model.setViewName("list/detail");
		model.addObject("testCoeus", testCoeus);
		return model;
	}
}
