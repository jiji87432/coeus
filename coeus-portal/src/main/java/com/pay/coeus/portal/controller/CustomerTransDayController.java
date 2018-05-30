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
import com.pay.coeus.api.inner.dubbo.CustomerActiveOrderFacade;
import com.pay.coeus.model.entity.CustomerTransDay;
import com.pay.commons.utils.Page;

/**
 * 日交易明细
 * @Description: 这里用一句话描述这个类的作用
 * @see: CustomerContactInfoController 此处填写需要参考的类
 * @version 2017年11月10日 上午10:41:15 
 * @author yuze.luo
 */
@Controller
@RequestMapping("/transDay")
public class CustomerTransDayController {
private static final Logger logger = LoggerFactory.getLogger(CustomerTransDayController.class);
	
	@Autowired
	private CustomerActiveOrderFacade customerActiveOrderFacade;
	
	// 挑转到列表页
	@RequestMapping("/list.action")
	public ModelAndView testCon(){
		ModelAndView model = new ModelAndView();
		model.setViewName("transDay/list");
		return model;
	}
	
	// 获取列表页数据
	@RequestMapping("/findAllList.action")
	@ResponseBody
	public String findAllList(HttpServletRequest request,@RequestParam Map<String,String> param){
		Page<List<CustomerTransDay>> list;
		try {
			if(param.get("createTimeStart") != null && param.get("createTimeStart").length() == 16){
				param.put("createTimeStart", param.get("createTimeStart")+":00");
			}
			if(param.get("createTimeEnd") != null && param.get("createTimeEnd").length() == 16){
				param.put("createTimeEnd", param.get("createTimeEnd")+":59");
			}
			logger.info("params {}", param);
			Page<List<CustomerTransDay>> page = (Page<List<CustomerTransDay>>)request.getAttribute("page");
			list = customerActiveOrderFacade.findAllList(page, param);
			return JSON.toJSONString(ReturnUtil.success(list));
		} catch (Exception e) {
			logger.error("transDay/findList", e);
			return JSON.toJSONString(ReturnUtil.error(e));
		}
	}
}
