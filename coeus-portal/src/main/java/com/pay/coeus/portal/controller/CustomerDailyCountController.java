package com.pay.coeus.portal.controller;

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
import com.pay.coeus.api.inner.dubbo.CustomerDailyCountFacade;
import com.pay.coeus.model.entity.CustomerDailyCount;
import com.pay.commons.utils.Page;

/**
 * 日活
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerDailyCountController 此处填写需要参考的类
 * @version 2017年11月10日 上午10:41:50 
 * @author yuze.luo
 */
@Controller
@RequestMapping("/dailyCount")
public class CustomerDailyCountController {
private static final Logger logger = LoggerFactory.getLogger(CustomerDailyCountController.class);
	
	
	@Autowired
	private CustomerDailyCountFacade customerDailyCountFacade;
	// 跳转到列表页
	@RequestMapping("/list.action")
	public ModelAndView testCon(){
		ModelAndView model = new ModelAndView();
		model.setViewName("dailyCount/list");
		return model;
	}
	// 获取列表页数据
	@RequestMapping("/findAllList.action")
	@ResponseBody
	public String findAllList(HttpServletRequest request,@RequestParam Map<String,String> param){
		Page<List<CustomerDailyCount>> list;
		try {
			logger.info("params {}", param);
			Page<List<CustomerDailyCount>> page = (Page<List<CustomerDailyCount>>)request.getAttribute("page");
			list = customerDailyCountFacade.findAllList(page, param);
			return JSON.toJSONString(ReturnUtil.success(list));
		} catch (Exception e) {
			logger.error("dailyCount/findAllList", e);
			return JSON.toJSONString(ReturnUtil.error(e));
		}
	}
	
}
